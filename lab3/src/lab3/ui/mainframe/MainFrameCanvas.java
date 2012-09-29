package lab3.ui.mainframe;

import java.awt.Canvas;
import java.awt.Graphics;
import lab3.Const;

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
