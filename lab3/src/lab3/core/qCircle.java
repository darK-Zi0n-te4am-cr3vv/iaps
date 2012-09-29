package lab3.core;

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
