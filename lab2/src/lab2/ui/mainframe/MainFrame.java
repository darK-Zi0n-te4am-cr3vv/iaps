/* MainFrame.java */

package lab2.ui.mainframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lab2.Const;
import lab2.core.IArea;
import lab2.core.Point;
import lab2.generation.AreaBuilder;
import lab2.ui.controls.IxControl;
import lab2.ui.controls.IxControlListener;
import lab2.ui.render.AreaRender;
import lab2.ui.render.AxisRender;
import lab2.core.Logger;

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
        add(_canvas, BorderLayout.CENTER);
        _canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               _ptX = (double)(e.getX() - Const.HALF_X) / Const.RSTEP;
               _ptY = (double)(Const.HALF_Y - e.getY()) / Const.RSTEP;
               
               updatePoint();
               
            }
        });

        addWindowListener(new MainFrameListener(this));
        
        AreaBuilder ab = new AreaBuilder();
        _area = ab.getArea();
        _xControl = ab.getXControl();
        _yControl = ab.getYControl();
        _rControl = ab.getRControl();
        
        JPanel controlPanel = new JPanel();
        add(controlPanel, BorderLayout.SOUTH);
        
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
        setResizable(false);
        
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
            g.fillOval(Const.HALF_X - 1 + (int)(_ptX * Const.RSTEP / _rParam), 
                    Const.HALF_Y - 1 - (int)(_ptY * Const.RSTEP / _rParam), 4, 4);
        }
    }
    
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
