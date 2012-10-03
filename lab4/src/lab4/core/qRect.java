package lab4.core;

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
