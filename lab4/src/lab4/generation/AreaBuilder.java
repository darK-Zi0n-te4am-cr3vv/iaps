package lab4.generation;

import lab4.Const;
import lab4.core.IArea;


public class AreaBuilder 
{
    GenerationContext _gc;
    private final IArea _area;
   
    
    public AreaBuilder()
    {
        _gc = new GenerationContext(Const.VARIANT, 1.0);
        
        /* NOTE: order is important! */
        _area =  _gc.createArea();
       
    }
    
   
    public IArea getArea()
    {
        return _area;
    }
}
