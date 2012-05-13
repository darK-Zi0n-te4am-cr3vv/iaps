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
