/* MainFrame.java */

package lab3.ui.mainframe;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lab3.Const;
import lab3.core.IArea;
import lab3.core.Logger;
import lab3.core.Point;
import lab3.generation.AreaBuilder;
import lab3.net.data.NetRequest;
import lab3.net.data.NetResponse;
import lab3.net.exceptions.CommunicationException;
import lab3.net.interfaces.INetClient;
import lab3.net.udp.DgramClient;
import lab3.ui.controls.IxControl;
import lab3.ui.controls.IxControlListener;
import lab3.ui.render.AreaRender;
import lab3.ui.render.AxisRender;


public class MainFrame extends JFrame 
{
    private final IxControl _xControl;
    private final IxControl _yControl;
    private final IxControl _rControl;
    private final IArea _area;
    
    private final AreaRender _areaRender = new AreaRender(Const.AREA_COLOR);
    private final AxisRender _axisRender = new AxisRender(Const.AXIS_COLOR);
    
    private final MainFrameCanvas _canvas = new MainFrameCanvas(this);
    
    
    private double _ptX = 0.0;
    private double _ptY = 0.0;
    private double _rParam = 1.0;
    
    private boolean _pointIsSet = false;
    private boolean _pointInArea = false;
    private boolean _serverUnreachable = false;
    private final String _host;
    
    public MainFrame(String host)
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
        
        _host = host;
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
            Color pointColor = _serverUnreachable 
                    ? Const.UNREACHABLE_COLOR 
                    : _pointInArea 
                        ? Const.IN_AREA_COLOR 
                        : Const.NOT_IN_AREA_COLOR;

            g.setColor(pointColor);
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
        
        
        INetClient client = new DgramClient();
        client.SetServerAddress(_host, Const.COMMUNICATION_PORT);
        
        try 
        {
            NetResponse response = client.SendRequest(new NetRequest(_ptX, _ptY, _rParam, Const.VARIANT));
            if (response!=null)
            {
                _serverUnreachable = false;
                _pointInArea = response.getPointStatus();
            }
            else 
            {
                _serverUnreachable = true;
            }
        }
        catch (CommunicationException ex)
        {
            _serverUnreachable = true;
        }
        
        repaint();
    }    
}
