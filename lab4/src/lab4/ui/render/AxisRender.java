package lab4.ui.render;

import java.awt.Color;
import java.awt.Graphics;
import lab4.Const;
import lab4.Format;

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
            
            graphics.drawString(Format.toString(x), Const.HALF_X + i * Const.STEP - Const.INDENT,
                   Const.HALF_Y - Const.INDENT);
            
            if ((i >= -4) && (i <= 4))
            {
                graphics.drawString(Format.toString(x), Const.HALF_X + Const.INDENT,
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
