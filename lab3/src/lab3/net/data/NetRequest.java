package lab3.net.data;

public class NetRequest
{
    private double _x;
    private double _y;
    private double _r;
    
    private int _seed;
    
    public NetRequest(double x, double y, double r, int seed)
    {
        _x = x;
        _y = y;
        _r = r;

        _seed = seed;
    }
    
    public double getX()
    {
        return  _x;
    }
    
    public double getY()
    {
        return _y;
    }
    
    public double getR()
    {
        return _r;
    }
    
    public int getSeed()
    {
        return _seed;
    }
}
