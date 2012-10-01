// ./src/lab2/Const.java
package lab2;

import java.awt.Color;

public class Const 
{
    public static final int WINDOW_SIZE_X = 900;
    public static final int WINDOW_SIZE_Y = 520;
    public static final int AREA_SIZE_X = 900;
    public static final int AREA_SIZE_Y = 400;

    public static final int LENGTH_X      = 430;
    public static final int LENGTH_Y      = 200;
    
    public static int INDENT        = 10;
    
    public static final int HALF_X        = AREA_SIZE_X / 2;
    public static final int HALF_Y        = AREA_SIZE_Y / 2;
    public static final int STEP          = 40; /* R/2 */
    public static final int RSTEP = STEP * 2;
    
    public static final int VARIANT = 2424;
    
    public static final Color AREA_COLOR = new Color(51, 153, 255);
    public static final Color AXIS_COLOR = Color.BLACK;
    
    public static final Color IN_AREA_COLOR = Color.YELLOW;
    public static final Color NOT_IN_AREA_COLOR = Color.RED;

    public static final String LOG_FILE = "points.log";
    
}
// ./src/lab2/core/ComplexArea.java
package lab2.core;

public class ComplexArea 
    implements IArea
{
    private final IArea[] _areas;
    
    public ComplexArea(IArea[] areas)
    {
        _areas = areas;
    }

    @Override
    public boolean checkPoint(Point pt)
    {
        for (IArea area : _areas)
        {
            if (area.checkPoint(pt)) return true;
        }
        
        return false;
    }

    @Override
    public void apply(IAreaVisitor visitor)
    {
        for (IArea area : _areas)
        {
            area.apply(visitor);
        }
    }
    
}
// ./src/lab2/core/IArea.java
package lab2.core;

public interface IArea 
{
    boolean checkPoint(Point pt);
    void apply(IAreaVisitor visitor);
}
// ./src/lab2/core/IAreaVisitor.java
package lab2.core;

public interface IAreaVisitor 
{
    public void Visit(qCircle aThis);
    public void Visit(qRect aThis);
    public void Visit(qTriangle aThis);   
}
// ./src/lab2/core/Logger.java
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.core;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import lab2.Const;

public class Logger 
{
    private PrintWriter _writer;
    
    public Logger()
    {
        try 
        {
            _writer = new PrintWriter(Const.LOG_FILE);
        } 
        catch (FileNotFoundException ex)
        {
            /* supress */
        }
        
    }
    
    public void log(double ptx, double pty, double r, boolean inArea)
    {
        if (_writer!=null)
        {
            if (inArea)
            {
                _writer.printf("In area: x=%f y=%f r=%f\n", ptx, pty, r);
            }
            else
            {
                _writer.printf("Not in area: x=%f y=%f r=%f\n", ptx, pty, r);
            }

            _writer.flush();
        }
    }
    
    @Override
    public void finalize() throws Throwable
    {
        super.finalize();
    
        if (_writer != null)
        {
            try
            {
                _writer.flush();
                _writer.close();
            }
            catch(Exception e)
            {
                /* supressed */
            }
        }
    } 
}
// ./src/lab2/core/Point.java
package lab2.core;

public class Point 
{
    private final double _x;
    private final double _y;

    public Point(double x, double y)
    {
        _x = x;
        _y = y;
    }
    
    public double getX()
    {
        return _x;
    }
    
    public double getY()
    {
        return _y;
    }
}
// ./src/lab2/core/qBase.java
package lab2.core;

abstract class qBase 
    implements IArea
{
    protected final int _quad;
    
    public qBase(int quad)
    {
        _quad = quad;
    }
    
    public int getQuad()
    {
        return  _quad;
    }
    
    protected abstract boolean checkPointInternal(Point pt);
    
    private boolean checkQuad(Point pt) 
    {
        switch (_quad)
        {
            case 1 : return pt.getX() >= 0 && pt.getY() >= 0;
            case 2 : return pt.getX() <= 0 && pt.getY() >= 0;
            case 3 : return pt.getX() <= 0 && pt.getY() <= 0;
            case 4 : return pt.getX() >= 0 && pt.getY() <= 0;
                            
            default: return false;
        }
    }
    
    @Override
    public boolean checkPoint(Point pt)
    {
        return checkQuad(pt) && checkPointInternal(pt);
    }
}
// ./src/lab2/core/qCircle.java
package lab2.core;

public class qCircle
    extends qBase
{
    private final double _r;
    private final double _rr;
    
    public qCircle(int quad, double r)
    {
        super(quad);
        
        _r = r;
        _rr = _r * _r;
    }

    public double getR()
    {
        return _r;
    }
    
    
    @Override
    public void apply(IAreaVisitor visitor) 
    {
        visitor.Visit(this);
    }

    @Override
    protected boolean checkPointInternal(Point pt) 
    {
        return (pt.getX() * pt.getX() + pt.getY() * pt.getY()) <= _rr;
    }
}
// ./src/lab2/core/qRect.java
package lab2.core;

public class qRect 
    extends qBase
{
    private final double _w;
    private final double _h;
    
    public qRect(int quad, double w, double h)
    {
        super(quad);
        
        _w = w;
        _h = h;
    }
    
    public double getH()
    {
        return _h;
    }
    
    public double getW()
    {
        return _w;
    }
    
    
    @Override
    public void apply(IAreaVisitor visitor)
    {
        visitor.Visit(this);
    }

    @Override
    protected boolean checkPointInternal(Point pt) 
    {
        return Math.abs(pt.getX()) <= _w && Math.abs(pt.getY()) <= _h; 
    }
    
}
// ./src/lab2/core/qTriangle.java
package lab2.core;

public class qTriangle 
    extends qBase
{
    private double _h;
    private double _w;
    private double _k;
 
    public qTriangle(int quad, double w, double h)
    {
        super(quad);

        _w = w;
        _h = h;

        _k = h / w;
    }

    public double getH()
    {
        return _h;
    }
    
    public double getW()
    {
        return _w;
    }
    
    @Override
    public void apply(IAreaVisitor visitor) 
    {
        visitor.Visit(this);
    }

    
    private double yMustBe(double x)
    {
        switch (_quad)
        {
            case 1   : return  _h - (_k * x);  
            case 2  : return  _h + (_k * x);  
            case 3 : return -_h - (_k * x);  
            case 4  : return -_h + (_k * x);			
        }

        return 0; 
    }

    
    @Override
    protected boolean checkPointInternal(Point pt) 
    {
       if (_quad == 1 || _quad == 2) 
           return pt.getY() <= yMustBe(pt.getX());
		
        return  pt.getY() >= yMustBe(pt.getX());
    }
}
// ./src/lab2/Entry.java
package lab2;

import lab2.ui.mainframe.MainFrame;

public class Entry 
{
    public static void main(String[] args)
    {
        MainFrame frame = new MainFrame();
        //NewJFrame jf=  new NewJFrame();
        //jf.setVisible(true);
    }
}
// ./src/lab2/generation/AreaBuilder.java
package lab2.generation;

import lab2.Const;
import lab2.core.IArea;
import lab2.ui.controls.IxControl;


public class AreaBuilder 
{
    GenerationContext _gc;
    private final IArea _area;
    private final IxControl _yControl;
    private final IxControl _xControl;
    private final IxControl _rControl;
    
    public AreaBuilder()
    {
        _gc = new GenerationContext(Const.VARIANT, 1.0);
        
        /* NOTE: order is important! */
        _area =  _gc.createArea();
        _xControl = _gc.lab2_ns("X=");
        _yControl = _gc.lab2_ns("Y=");
        _rControl = _gc.lab2_nn("R=");
    }
    
    public IxControl getXControl()
    {
        return  _xControl;
    }
    
    public IxControl getYControl()
    {
        return  _yControl;
    }
    
    public IxControl getRControl()
    {
        return _rControl;
    }
    
    public IArea getArea()
    {
        return _area;
    }
}
// ./src/lab2/generation/GenerationContext.java
package lab2.generation;

import java.util.Random;
import lab2.core.*;
import lab2.ui.controls.*;

public class GenerationContext 
{
    private Random r;
    private double _R;
    private int _variant;
    private boolean[] quarters;

    /* COMMON */

    /* WORKAROUND: to get compatibility with original AreaCanvas.java */
    static final int[] Q_TABLE = new int[] 
            { 1, 4, 3, 2 }; 

    private int getFreeQuarter()
    {
        int q;
        do q = Math.abs(r.nextInt()) % 4;
        while (quarters[q]);

        quarters[q] = true;
        return Q_TABLE[q];
    }

    private qCircle createCircle()
    {
        double radius = 0;

        switch(Math.abs(r.nextInt()) % 2)
        {
                case 0: radius = _R; break;
                case 1: radius = _R / 2; break;
        }

        int q = getFreeQuarter();

        return new qCircle(q, radius);
    }

    private qRect createSquare()
    {
        int q = getFreeQuarter();
        double w = 0, h = 0;

        switch(Math.abs(r.nextInt()) % 3)
        {
                case 0:
                        w = _R; // R
                        h = _R; // R
                break;

                case 1: 
                        w = _R / 2; // R/2
                        h = _R; // R
                break;

                case 2: 
                        w = _R; // R
                        h = _R / 2; // R/2
                break;
        }

        return new qRect(q, w, h);
    }


    private qTriangle createTriangle()
    {
        double w = 0, h = 0;

        int q = getFreeQuarter();

        w = (Math.abs(r.nextInt()) % 2) == 0 ? _R / 2 : _R; 
        h = (Math.abs(r.nextInt()) % 2) == 0 ? _R / 2 : _R; 

        return new qTriangle(q, w, h);
    }

    public IArea createArea()
    {
        r.setSeed(_variant);

        return new ComplexArea(new IArea[] {
            createCircle(),
            createSquare(),
            createTriangle()
        });
    }

    /* LAB 1 POINTS */
    private int lab1_nn()
    {
        return (Math.abs(r.nextInt()) % 5) - 2;
    }

    private int lab1_ns()
    {
        return (Math.abs(r.nextInt()) % 3) + 3;
    }

    public Point[] generatePointsForLab1()
    {
        Point[] pts = {
            new Point(lab1_ns(), lab1_ns()),
            new Point(lab1_ns(), -lab1_ns()),
            new Point(-lab1_ns(), -lab1_ns()),
            new Point(-lab1_ns(), lab1_ns()),
            new Point(lab1_nn(), lab1_nn())
        };

        return pts;
    }

    /* LAB 2 COMPONENTS */
    
    public IxControl lab2_ns(String name)
    {
        int q = Math.abs(r.nextInt()) % 24;
		
        switch(q)
        {
            case 0: return new xButton(name, new double[] {-4,-3,-2,-1,0,1,2,3,4});
            case 1: return new xButton(name, new double[] {-2,-1.5,-1,-0.5,0,0.5,1,1.5,2});
            case 2: return new xButton(name, new double[] {-5,-4,-3,-2,-1,0,1,2,3});
            case 3: return new xButton(name, new double[] {-3,-2,-1,0,1,2,3,4,5});
            
            case 4: return new xCheckbox(name, new double[] {-4,-3,-2,-1,0,1,2,3,4});
            case 5: return new xCheckbox(name, new double[] {-2,-1.5,-1,-0.5,0,0.5,1,1.5,2});
            case 6: return new xCheckbox(name, new double[] {-5,-4,-3,-2,-1,0,1,2,3});
            case 7: return new xCheckbox(name, new double[] {-3,-2,-1,0,1,2,3,4,5});
            
            case 8: return new xChoice(name, new double[] {-4,-3,-2,-1,0,1,2,3,4});
            case 9: return new xChoice(name, new double[] {-2,-1.5,-1,-0.5,0,0.5,1,1.5,2});
            case 10: return new xChoice(name, new double[] {-5,-4,-3,-2,-1,0,1,2,3});
            case 11: return new xChoice(name, new double[] {-3,-2,-1,0,1,2,3,4,5});
            
            case 12: return new xList(name, new double[] {-4,-3,-2,-1,0,1,2,3,4});
            case 13: return new xList(name, new double[] {-2,-1.5,-1,-0.5,0,0.5,1,1.5,2});
            case 14: return new xList(name, new double[] {-5,-4,-3,-2,-1,0,1,2,3});
            case 15: return new xList(name, new double[] {-3,-2,-1,0,1,2,3,4,5});
            
            case 16: return  new xTextfield(name, -3, 3);
            case 17: return  new xTextfield(name, -5, 5);
            case 18: return  new xTextfield(name, -5, 3);
            case 19: return  new xTextfield(name, -3, 5);

            case 20: return new xScrollbar(name, -3, 3);
            case 21: return new xScrollbar(name, -5, 5);
            case 22: return new xScrollbar(name, -5, 3);
            case 23: return new xScrollbar(name, -3, 5);
            
            default: return  null;
        }
       
    }
    
    public IxControl lab2_nn(String name)
    {
        int q = Math.abs(r.nextInt()) % 12;
		
        switch(q)
        {
            case 0: return new xButton(name, new double[] {1,2,3,4, 5});
            case 1: return new xButton(name, new double[] {1,1.5,2,2.5,3});
            
            case 2: return new xCheckbox(name, new double[] {1,2,3,4, 5});
            case 3: return new xCheckbox(name, new double[]  {1,1.5,2,2.5,3});
            
            case 4: return new xChoice(name, new double[] {1,2,3,4, 5});
            case 5: return new xChoice(name, new double[]{1,1.5,2,2.5,3});
            
            case 6: return new xList(name, new double[] {1,2,3,4,5});
            case 7: return new xList(name, new double[] {1,1.5,2,2.5,3});
           
            case 8: return new xTextfield(name, 2, 5);
            case 9: return new xTextfield(name, 1, 4);

            case 10: return new xScrollbar(name, 1, 4);
            case 11: return new xScrollbar(name, 2, 6);
        
            default: return null;
        }
    }
    
    
    
    public GenerationContext(int variant, double R)
    {
        r = new Random(_variant = variant);
        _R = R;

        quarters = new boolean[] {false, false, false, false};
    }
}
// ./src/lab2/ui/controls/IxControl.java
package lab2.ui.controls;

import javax.swing.JPanel;

public interface IxControl 
{ 
    void setListener(IxControlListener listener);
    void setPanel(JPanel panel);
}// ./src/lab2/ui/controls/IxControlListener.java
package lab2.ui.controls;

public interface IxControlListener 
{
    void valueChanged(double newValue);
}
// ./src/lab2/ui/controls/xBase.java
package lab2.ui.controls;

import java.awt.Label;
import javax.swing.JPanel;

public class xBase
    implements IxControl
{
    protected  IxControlListener _listener;
    protected JPanel _panel;

    public xBase(String controlName)
    {
        _panel = new JPanel();
        
        Label lb = new Label(controlName);
        _panel.add(lb);
    }
    
    protected void notifyValueChanged(double newValue)
    {
        if (_listener != null) 
            _listener.valueChanged(newValue);
    }
    
    @Override
    public void setListener(IxControlListener listener)
    {
        _listener = listener;
        
    }

    @Override
    public void setPanel(JPanel panel) 
    {
        panel.add(_panel);
    }
}
// ./src/lab2/ui/controls/xButton.java
package lab2.ui.controls;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class xButton 
    extends xBase
{
    public xButton(String name, double[] values)
    {
        super(name);
     
        for (double value : values)
        {
            Button bt = new  Button(Double.toString(value));
            bt.addActionListener(new BtActionListener(value));
            
            _panel.add(bt);
            
        }
        
    }
    
    
    class BtActionListener 
        implements ActionListener
    {
        private final double _value;
        
        public BtActionListener(double value)
        {
            _value = value;
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            notifyValueChanged(_value);            
        }
    }
}
// ./src/lab2/ui/controls/xCheckbox.java
package lab2.ui.controls;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class xCheckbox 
    extends xBase
{
    public xCheckbox(String name, double[] values)
    {
        super(name);
     
        CheckboxGroup cbg = new CheckboxGroup();
        for (double value : values)
        {
            Checkbox cb = new Checkbox(Double.toString(value), false, cbg);
            cb.addItemListener(new CbActionListener(value));
            
            _panel.add(cb);
        }
    }
    
    
     class CbActionListener 
        implements ItemListener
    {
        private final double _value;
        
        public CbActionListener(double value)
        {
            _value = value;
        }

        @Override
        public void itemStateChanged(ItemEvent e) 
        { 
            notifyValueChanged(_value);            
        }

        
    }
}
// ./src/lab2/ui/controls/xChoice.java
package lab2.ui.controls;

import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class xChoice 
    extends xBase
{
    private Choice _choice;
    private final double[] _values;
    
    public xChoice(String name, double[] values)
    {
        super(name);
        
        _choice = new Choice();
        _values = values;
        
        for (double val : values) 
        {
           _choice.add(Double.toString(val));
        }
        
        _choice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) 
            {
                notifyValueChanged(_values[_choice.getSelectedIndex()]);
            }
        });
        
        _panel.add(_choice);
    }
}
// ./src/lab2/ui/controls/xList.java
package lab2.ui.controls;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class xList 
    extends xBase
{
    private final List _list;
    private double[] _values;
    
    public xList(String name, double[] values)
    {
        super(name);
        
        _list = new List(2);
        _values = values;
        
        for (double val : values) 
        {
           _list.add(Double.toString(val));
        }
        
       
        _list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                notifyValueChanged(_values[_list.getSelectedIndex()]);
            }
        });
        
        _panel.add(_list);
    }
}
// ./src/lab2/ui/controls/xScrollbar.java
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.ui.controls;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import lab2.ui.controls.xBase;


/**
 *
 * @author C.c
 */
public class xScrollbar
    extends xBase
{
    private final java.awt.Scrollbar _scrollbar;
    
    public xScrollbar(String name, int min, int max)
    {
        super(name);
        
        _scrollbar = new java.awt.Scrollbar(java.awt.Scrollbar.HORIZONTAL, 0, 1, min, max);
        _scrollbar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) 
            {
                notifyValueChanged(e.getValue());
            }
        });
        
        _panel.add(_scrollbar);
    }
    
    
}
// ./src/lab2/ui/controls/xTextfield.java
package lab2.ui.controls;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class xTextfield
    extends xBase
{
    public xTextfield(String name, double min, double max)
    {
        super(name);
        
        JTextField tf = new JTextField(7);
        tf.getDocument().addDocumentListener(new TfDocumentListener(min, max, tf));
        
        _panel.add(tf);
    }
    
    class TfDocumentListener 
        implements DocumentListener
    {
        private final double _min;
        private final double _max;
        private final JTextField _tf;
        
        public TfDocumentListener(double min, double max, JTextField tf)
        {
            _min = min;
            _max = max;
            _tf = tf;
        }
       
        private void parse(DocumentEvent e)
        {
            double input;
            
            try 
            { 
                input = Double.parseDouble(_tf.getText()); 
            }
            catch(NumberFormatException ex)
            {
                return;
            }
            
            if (input < _min || input > _max) 
            {
                _tf.setForeground(Color.red);
                _tf.setToolTipText("Input must be between " 
                        + Double.toString(_min) 
                        + " and " 
                        + Double.toString(_max) 
                        + ".");
            }
            else
            {
                _tf.setToolTipText(null);
                _tf.setForeground(Color.black);
                
                notifyValueChanged(input);
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            parse(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            parse(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            parse(e);
        }
       
    }
}
// ./src/lab2/ui/mainframe/MainFrame.java
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
// ./src/lab2/ui/mainframe/MainFrameCanvas.java
package lab2.ui.mainframe;

import java.awt.Canvas;
import java.awt.Graphics;
import lab2.Const;

public class MainFrameCanvas
    extends Canvas
{
    private final MainFrame _frame;
    
    public MainFrameCanvas(MainFrame frame) 
    {
        _frame = frame;
        setSize(Const.AREA_SIZE_X, Const.AREA_SIZE_Y);
    }
    
    @Override
    public void paint(Graphics g)
    {        
        _frame.drawArea(g);
    }
    
}
// ./src/lab2/ui/mainframe/MainFrameListener.java
package lab2.ui.mainframe;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MainFrameListener extends WindowAdapter
{
    MainFrame _frame;
    public  MainFrameListener(MainFrame frame)
    {
        _frame = frame;
    }
   
}
// ./src/lab2/ui/render/AreaRender.java
package lab2.ui.render;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import lab2.Const;
import lab2.core.IAreaVisitor;
import lab2.core.qCircle;
import lab2.core.qRect;
import lab2.core.qTriangle;

public class AreaRender 
    implements IAreaVisitor
{
    private Graphics _graphics;
    private Color _color;
  
    public AreaRender(Color areaColor)
    {
        _color = areaColor;
    }
    
    public void setGraphics(Graphics graphics)
    {
        _graphics = graphics;
    }
  
    
    @Override
    public void Visit(qCircle circle) 
    {
         int r = (int)(circle.getR() * Const.RSTEP);
         
         _graphics.setColor(_color);
         _graphics.fillArc(Const.HALF_X - r, Const.HALF_Y - r,  /* x, y */
                 r * 2, r * 2,  /* w, h */
                 (circle.getQuad() - 1) * 90, 90);
    }

    @Override
    public void Visit(qRect rect) 
    {
        int w = (int)(rect.getW() * Const.RSTEP);
        int h = (int)(rect.getH() * Const.RSTEP);
        
        int x = rect.getQuad() == 2 || rect.getQuad() == 3 ? Const.HALF_X - w : Const.HALF_X;
        int y = rect.getQuad() == 1 || rect.getQuad() == 2 ? Const.HALF_Y - h : Const.HALF_Y;
        
        _graphics.setColor(_color);
        _graphics.fillRect(x, y, w, h);
    }

    @Override
    public void Visit(qTriangle triangle) 
    {
        int w = (int)(triangle.getW() * Const.RSTEP);
        int h = (int)(triangle.getH() * Const.RSTEP);
        
        w = triangle.getQuad() == 3 || triangle.getQuad() == 2 ? -w : w;
        h = triangle.getQuad() == 1 || triangle.getQuad() == 2 ? -h : h;
        
        int[] xPoints = new int[] {
            Const.HALF_X, Const.HALF_X, Const.HALF_X + w
        };
        
        int[] yPoints = new int[] {
            Const.HALF_Y, Const.HALF_Y + h, Const.HALF_Y
        };
        
        _graphics.setColor(_color);
        _graphics.fillPolygon(xPoints, yPoints, 3);
    }
}
// ./src/lab2/ui/render/AxisRender.java
package lab2.ui.render;

import java.awt.Color;
import java.awt.Graphics;
import lab2.Const;

public class AxisRender 
{
    private final Color _color;
    public AxisRender(Color axisColor)
    {
        _color = axisColor;
    }
    
    private void drawAxisLines(Graphics graphics)
    {
        graphics.drawLine(Const.HALF_X - Const.LENGTH_X, Const.HALF_Y, Const.HALF_X + Const.LENGTH_X, Const.HALF_Y);
        graphics.drawLine(Const.HALF_X, Const.HALF_Y - Const.LENGTH_Y, Const.HALF_X, Const.HALF_Y + Const.LENGTH_Y);
    }
    
    private void drawAxisArrows(Graphics graphics)
    {
         graphics.drawLine(Const.HALF_X, Const.HALF_Y - Const.LENGTH_Y,
                Const.HALF_X - Const.INDENT / 2, Const.HALF_Y - Const.LENGTH_Y + Const.INDENT);
        graphics.drawLine(Const.HALF_X, Const.HALF_Y - Const.LENGTH_Y,
                Const.HALF_X + Const.INDENT / 2, Const.HALF_Y - Const.LENGTH_Y + Const.INDENT);
        graphics.drawLine(Const.HALF_X + Const.LENGTH_X, Const.HALF_Y,
                Const.HALF_X + Const.LENGTH_X - Const.INDENT, Const.HALF_Y - Const.INDENT / 2);
        graphics.drawLine(Const.HALF_X + Const.LENGTH_X, Const.HALF_Y,
                Const.HALF_X + Const.LENGTH_X - Const.INDENT, Const.HALF_Y + Const.INDENT / 2);
        
    }
    
    private void drawAxisSerifs(Graphics graphics)
    {
        int i;
        double x;
        
        for (i = -4; i <= 4; i++) 
        {
            graphics.drawLine(Const.HALF_X - Const.INDENT / 2, Const.HALF_Y + i * Const.STEP,
                        Const.HALF_X + Const.INDENT / 2, Const.HALF_Y + i * Const.STEP);
            
        }
            
        for (i = -10; i<= 10; i++)
        {
            graphics.drawLine(Const.HALF_X + i * Const.STEP, Const.HALF_Y - Const.INDENT / 2,
                    Const.HALF_X + i * Const.STEP, Const.HALF_Y + Const.INDENT / 2);
        }
    }
    
    private void drawAxisText(Graphics graphics, double r)
    {
        int i;
        double x;
        
        graphics.drawString("Y", Const.HALF_X + Const.INDENT, Const.HALF_Y - Const.LENGTH_Y + Const.INDENT);
        graphics.drawString("X", Const.HALF_X + Const.LENGTH_X - Const.INDENT, Const.HALF_Y - Const.INDENT);
        for (x = -5 * r, i = -10; i <= 10; i++, x += r/2)
        {
            if (i == 0) continue;
            
            graphics.drawString(Double.toString(x), Const.HALF_X + i * Const.STEP - Const.INDENT,
                   Const.HALF_Y - Const.INDENT);
            
            if ((i >= -4) && (i <= 4))
            {
                graphics.drawString(Double.toString(x), Const.HALF_X + Const.INDENT,
                    Const.HALF_Y - i * Const.STEP + Const.INDENT / 2);
            }
        }
    }
    
    public void renderAxis(Graphics graphics, double r)
    {
        graphics.setColor(_color);
        
        drawAxisLines(graphics);
        drawAxisArrows(graphics);
        drawAxisSerifs(graphics);
        drawAxisText(graphics, r);
       
       

    }
}
