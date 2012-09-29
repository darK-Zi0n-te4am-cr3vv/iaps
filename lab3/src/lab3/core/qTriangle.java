package lab3.core;

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
