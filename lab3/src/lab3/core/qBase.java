package lab3.core;

abstract class qBase 
    implements IArea
{
    protected final int _quad;
    
    public qBase(int quad)
    {
        _quad = quad;
    }
    
    public int getQuad()
    {
        return  _quad;
    }
    
    protected abstract boolean checkPointInternal(Point pt);
    
    private boolean checkQuad(Point pt) 
    {
        switch (_quad)
        {
            case 1 : return pt.getX() >= 0 && pt.getY() >= 0;
            case 2 : return pt.getX() <= 0 && pt.getY() >= 0;
            case 3 : return pt.getX() <= 0 && pt.getY() <= 0;
            case 4 : return pt.getX() >= 0 && pt.getY() <= 0;
                            
            default: return false;
        }
    }
    
    @Override
    public boolean checkPoint(Point pt)
    {
        return checkQuad(pt) && checkPointInternal(pt);
    }
}
