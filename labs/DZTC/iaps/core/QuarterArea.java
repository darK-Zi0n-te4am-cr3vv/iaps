package DZTC.iaps.core;

public abstract class QuarterArea
	implements IArea
{
	protected Quarter _q;
	
	public QuarterArea(Quarter q)
	{
		_q = q;
	}

	protected boolean inQuarter(Point pt)
	{
		switch (_q)
		{
			case I   : return pt.getX() >= 0 && pt.getY() >= 0;
			case II  : return pt.getX() <= 0 && pt.getY() >= 0;
			case III : return pt.getX() <= 0 && pt.getY() <= 0;
			case IV  : return pt.getX() >= 0 && pt.getY() <= 0;
			
			default : return false;
		}
	}
	
	protected abstract boolean isContainsPt(Point pt);
	
	public boolean containsPt(Point pt)
	{
		return inQuarter(pt) && isContainsPt(pt);
	}
}