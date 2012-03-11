package DZTC.iaps.core;

public class QuarterTriangle extends QuarterArea
{
	private double _h;
	private double _w;
	private double _k;

	public QuarterTriangle(Quarter q, double h, double w)
	{
		super(q);
		
		_w = w;
		_h = h;
		
		_k = h / w;
	}

	private double yMustBe(double x)
	{
		switch (_q)
		{
			case I   : return  _h - (_k * x);  
			case II  : return  _h + (_k * x);  
			case III : return -_h - (_k * x);  
			case IV  : return -_h + (_k * x);			
		}
		
		return 0; 
	}
	
	@Override protected boolean isContainsPt(Point pt)
	{
		if (_q == Quarter.I || _q == Quarter.II) 
			return pt.getY() <= yMustBe(pt.getX());
		
		return  pt.getY() >= yMustBe(pt.getX());
	}
	
	@Override public String toString()
	{
		return "[qtrig: W = " + Double.toString(_w) + ", H = " + Double.toString(_h) + ", Q = " + _q.toString() + "]";
	}
}