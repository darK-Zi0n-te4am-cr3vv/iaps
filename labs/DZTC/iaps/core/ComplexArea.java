package DZTC.iaps.core;

public class ComplexArea
	implements IArea
{
	private IArea[] _areas;
	
	public ComplexArea(IArea[] areas)
	{
		_areas = areas;
	}

	public boolean containsPt(Point pt)
	{
		for (IArea area : _areas) 
			if (area.containsPt(pt)) return true;
		
		return false;
	}
	
	@Override public String toString()
	{
		String s = "";
	
		for (IArea area : _areas) s += area.toString() + "\n";
		
		return s;
	}
}