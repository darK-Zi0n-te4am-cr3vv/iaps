package lab4.ui.mainframe;

import java.awt.Canvas;
import java.awt.Graphics;
import lab4.Const;

public class MainFrameCanvas
    extends Canvas
{
    private final MainPanel _frame;
    
    public MainFrameCanvas(MainPanel frame) 
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
