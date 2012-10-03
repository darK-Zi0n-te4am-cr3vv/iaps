package lab4.core;

public class ComplexArea 
    implements IArea
{
    private final IArea[] _areas;
    
    public ComplexArea(IArea[] areas)
    {
        _areas = areas;
    }

    @Override
    public boolean checkPoint(Point pt)
    {
        for (IArea area : _areas)
        {
            if (area.checkPoint(pt)) return true;
        }
        
        return false;
    }

    @Override
    public void apply(IAreaVisitor visitor)
    {
        for (IArea area : _areas)
        {
            area.apply(visitor);
        }
    }
    
}
