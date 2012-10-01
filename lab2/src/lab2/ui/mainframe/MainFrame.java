/* MainFrame.java */

package lab2.ui.mainframe;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lab2.Const;
import lab2.core.IArea;
import lab2.core.Logger;
import lab2.core.Point;
import lab2.generation.AreaBuilder;
import lab2.ui.controls.IxControl;
import lab2.ui.controls.IxControlListener;
import lab2.ui.render.AreaRender;
import lab2.ui.render.AxisRender;


public class MainFrame extends JFrame 
{
    private final IxControl _xControl;
    private final IxControl _yControl;
    private final IxControl _rControl;
    private final IArea _area;
    
    private final AreaRender _areaRender = new AreaRender(Const.AREA_COLOR);
    private final AxisRender _axisRender = new AxisRender(Const.AXIS_COLOR);
    
    private final MainFrameCanvas _canvas = new MainFrameCanvas(this);
    
    private final Logger _logger = new Logger();
    
    private double _ptX = 0.0;
    private double _ptY = 0.0;
    private double _rParam = 1.0;
    private boolean _pointIsSet = false;
    private boolean _pointInArea = false;
    
    public MainFrame()
    {   
        Dimension canvasSize = new Dimension(Const.AREA_SIZE_X, Const.AREA_SIZE_Y);
        
        JPanel canvasPanel = new JPanel();
        JPanel canvasPanel2 = new JPanel();
     
        Filler fl = new Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32000, 32000));
        
        canvasPanel2.setPreferredSize(canvasSize);
        canvasPanel2.setMaximumSize(canvasSize);
        _canvas.setPreferredSize(canvasSize);
        _canvas.setMaximumSize(canvasSize);
     
        
        
        _canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               _ptX = (double)(e.getX() - Const.HALF_X) * _rParam / Const.RSTEP;
               _ptY = (double)(Const.HALF_Y - e.getY()) * _rParam / Const.RSTEP;
               
               updatePoint();
               
            }
        });
        
        canvasPanel2.add(_canvas);
        
        canvasPanel.add(Box.createVerticalGlue(), BorderLayout.PAGE_START);
        canvasPanel.add(canvasPanel2);
        canvasPanel.add(Box.createVerticalGlue(), BorderLayout.PAGE_END);
        
        add(canvasPanel, BorderLayout.CENTER);
        
        addWindowListener(new MainFrameListener(this));
        
        AreaBuilder ab = new AreaBuilder();
        _area = ab.getArea();
        _xControl = ab.getXControl();
        _yControl = ab.getYControl();
        _rControl = ab.getRControl();
        
        JPanel controlPanel = new JPanel();
        add(controlPanel, BorderLayout.PAGE_END);
        
        _xControl.setPanel(controlPanel);
        _yControl.setPanel(controlPanel);
        _rControl.setPanel(controlPanel);
     
        _xControl.setListener(new IxControlListener() {
            @Override
            public void valueChanged(double newValue) {
                _ptX = newValue;
                updatePoint();
            }
        });
        
        _yControl.setListener(new IxControlListener() {
            @Override
            public void valueChanged(double newValue) {
                _ptY = newValue;
                updatePoint();
            }
            
        });
        
        _rControl.setListener(new IxControlListener() {
            @Override
            public void valueChanged(double newValue) {
                _rParam = newValue;
                updatePoint();
            }
        });
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Const.WINDOW_SIZE_X, Const.WINDOW_SIZE_Y); 
        //setResizable(false);
        
        setVisible(true);
    }

    void drawArea(Graphics g) 
    { 
         g.setColor(Color.white);
         g.fillRect(0, 0, Const.AREA_SIZE_X, Const.AREA_SIZE_Y);
    
        _areaRender.setGraphics(g);
        _area.apply(_areaRender);
    
        _axisRender.renderAxis(g, _rParam);
           
        if (_pointIsSet)
        {
            g.setColor(_pointInArea ? Const.IN_AREA_COLOR : Const.NOT_IN_AREA_COLOR);
            g.fillOval(Const.HALF_X - 2 + (int)(_ptX * Const.RSTEP / _rParam), 
                    Const.HALF_Y - 2 - (int)(_ptY * Const.RSTEP / _rParam), 5, 5);
        }
    }
    
    @Override
    public void paint(Graphics g) 
    {
        if (_canvas.getGraphics() != null) 
        {
            _canvas.repaint();
        }
    }
    
    private void updatePoint()
    {
        _pointIsSet = true;
        _pointInArea = _area.checkPoint(new Point(_ptX/_rParam, _ptY/_rParam));
        
        _logger.log(_ptX, _ptX, _rParam, _pointInArea);
        
        repaint();
    }    
}
