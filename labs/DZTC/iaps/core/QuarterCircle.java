package DZTC.iaps.core;

public class QuarterCircle
	extends QuarterArea
{
	private double _rr;
	

	public QuarterCircle(Quarter q, double r)
	{
		super(q);
		
		_rr = r * r;
	}

	@Override protected boolean isContainsPt(Point pt)
	{
		return pt.getX() * pt.getX() + pt.getY() * pt.getY() <= _rr;
	}
}