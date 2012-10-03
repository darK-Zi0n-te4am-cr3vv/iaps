/* MainFrame.java */

package lab4.ui.mainframe;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.Box;
import javax.swing.JPanel;
import lab4.Const;
import lab4.Format;
import lab4.core.IArea;
import lab4.core.Point;
import lab4.generation.AreaBuilder;
import lab4.localisation.LocalisationListener;
import lab4.ui.mainframe.animation.AnimationScheduler;
import lab4.ui.mainframe.animation.AnimationTask;
import lab4.ui.mainframe.animation.AnimationTask.StepResult;
import lab4.ui.render.AreaRender;
import lab4.ui.render.AxisRender;


public class MainPanel 
extends JPanel 
implements LocalisationListener<Localisation>
{
    private final IArea _area;
    
    private final AreaRender _areaRender = new AreaRender(Const.AREA_COLOR);
    private final AxisRender _axisRender = new AxisRender(Const.AXIS_COLOR);
    
    private final MainFrameCanvas _canvas = new MainFrameCanvas(this);
    
    private double _rParam = 1.0;
    
    private boolean _pointIsSet = false;
    private boolean _pointInArea = false;
     
    private TextField _xField = new TextField();
    private TextField _yField = new TextField();
    private TextField _rField= new TextField();
    
    private Label _xLabel = new  Label();
    private Label _yLabel = new  Label();
    private Label _rLabel = new  Label();
    
    
    private AnimationScheduler _scheduler;
    
    public MainPanel()
    {   
        Dimension canvasSize = new Dimension(Const.AREA_SIZE_X, Const.AREA_SIZE_Y);
        
        setLayout(new BorderLayout());
        
        JPanel canvasPanel = new JPanel();
        JPanel canvasPanel2 = new JPanel();
     
        canvasPanel2.setPreferredSize(canvasSize);
        canvasPanel2.setMaximumSize(canvasSize);
        _canvas.setPreferredSize(canvasSize);
        _canvas.setMaximumSize(canvasSize);
     
        
        final DotAnimationTask.PointProjectionProvider ppp = new DotAnimationTask.PointProjectionProvider() {
            @Override
            public java.awt.Point project(double x, double y) 
            {
                int px = Const.HALF_X + (int)((x * Const.RSTEP) / _rParam);
               int py = Const.HALF_Y - (int)((y * Const.RSTEP) / _rParam);
            
               return new java.awt.Point(px, py);
            }
        };
        
        _canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
               double ptX = (double)(e.getX() - Const.HALF_X)   / Const.RSTEP;
               double ptY = (double)(Const.HALF_Y - e.getY())/ Const.RSTEP;
        
               boolean pointOk = _area.checkPoint(new Point(ptX, ptY));
               
               Color color = pointOk ? Const.IN_AREA_COLOR:Const.NOT_IN_AREA_COLOR;
               
               _scheduler.addTask(new DotAnimationTask(color, ptX*_rParam, ptY*_rParam, ppp));
               
               _xField.setText(Format.toString(ptX*_rParam));
               _yField.setText(Format.toString(ptY*_rParam));
               
               
            }
        });
        
        canvasPanel2.add(_canvas);
        
        canvasPanel.add(Box.createVerticalGlue(), BorderLayout.PAGE_START);
        canvasPanel.add(canvasPanel2);
        canvasPanel.add(Box.createVerticalGlue(), BorderLayout.PAGE_END);
        
        add(canvasPanel, BorderLayout.NORTH);
        
        //addWindowListener(new MainFrameListener(this));
        
        AreaBuilder ab = new AreaBuilder();
        _area = ab.getArea();
 
        JPanel controlPanel = new JPanel();
        add(controlPanel, BorderLayout.SOUTH);

        _xField.setColumns(10);
        _yField.setColumns(10);
        _rField.setColumns(10);
        
        _xField.setText("0");
        _yField.setText("0");
        _rField.setText("0");
        
        controlPanel.add(_xLabel);
        controlPanel.add(_xField);
        
        controlPanel.add(_yLabel);
        controlPanel.add(_yField);
        
        controlPanel.add(_rLabel);
        controlPanel.add(_rField);
        
        _rField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    _rParam = Format.parseDouble(e.getActionCommand());
                }
                catch(Exception ex)
                {
                    
                }
            }
        });
        
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double ptx, pty;
                
                try
                {
                    ptx = Format.parseDouble(_xField.getText());
                    pty = Format.parseDouble(_yField.getText());
                    
                    boolean pointOk = _area.checkPoint(new Point(ptx / _rParam, pty/_rParam));
               
                Color color = pointOk ? Const.IN_AREA_COLOR:Const.NOT_IN_AREA_COLOR;

                    _scheduler.addTask(new DotAnimationTask(color, ptx, pty, ppp));
                }
                catch(Exception ex)
                {
                    
                }
            }
        };
        
        _xField.addActionListener(al);
        _yField.addActionListener(al);
        
        
        setSize(Const.WINDOW_SIZE_X, Const.WINDOW_SIZE_Y); 
        
        _scheduler = new AnimationScheduler(_canvas, 100);
        _scheduler.addTask(new AnimationTask() {

            @Override
            protected StepResult step(Graphics graphics, long delta, long totalMills) 
            {
                drawArea(graphics);
                
                return AnimationTask.StepResult.CONTINUE;
            }
        });
        
        
        
        setVisible(true);
    }

    void drawArea(Graphics g) 
    { 
         g.setColor(Color.white);
         g.fillRect(0, 0, Const.AREA_SIZE_X, Const.AREA_SIZE_Y);
    
        _areaRender.setGraphics(g);
        _area.apply(_areaRender);
    
        _axisRender.renderAxis(g, _rParam);
           
     
    }
    
    
    
    @Override
    public void paint(Graphics g) 
    {
        if (_canvas.getGraphics() != null) 
        {
            _canvas.repaint();
        }
    }
    
    
    @Override
    public void applyLocalisation(Localisation localisation)
    {
        _xLabel.setText(localisation.Xeq);
       _yLabel.setText(localisation.Yeq);
       _rLabel.setText(localisation.Req);   
    }

    @Override
    public void addChildListeners(Localisation localisation) 
    {
    
    }

    @Override
    public void removeChildListeners(Localisation localisation)
    {
    }

    void close() 
    {
        _scheduler.stop();
    }
}
