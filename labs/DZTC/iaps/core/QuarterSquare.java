package DZTC.iaps.core;

public class QuarterSquare
	extends QuarterArea
{
	private double _h;
	private double _w;
	

	public QuarterSquare(Quarter q, double h, double w)
	{
		super(q);
		
		_w = w;
		_h = w;
	}

	@Override protected boolean isContainsPt(Point pt)
	{
		return Math.abs(pt.getX()) <= _w && Math.abs(pt.getY()) <= _h;
	}
}