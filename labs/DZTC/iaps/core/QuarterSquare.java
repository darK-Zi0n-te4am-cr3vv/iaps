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
		_h = h;
	}

	@Override protected boolean isContainsPt(Point pt)
	{
		return Math.abs(pt.getX()) <= _w && Math.abs(pt.getY()) <= _h;
	}
	
	@Override public String toString()
	{
		return "[qsquare: W = " + Double.toString(_w) + ", H = " + Double.toString(_h) + ", Q = " + _q.toString() + "]";
	}
}