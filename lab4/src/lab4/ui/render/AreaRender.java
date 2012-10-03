package lab4.ui.render;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import lab4.Const;
import lab4.core.IAreaVisitor;
import lab4.core.qCircle;
import lab4.core.qRect;
import lab4.core.qTriangle;

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
