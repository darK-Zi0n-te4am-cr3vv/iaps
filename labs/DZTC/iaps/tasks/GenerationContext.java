package DZTC.iaps.tasks;

import java.util.Random;
import DZTC.iaps.core.*;

class GenerationContext
{
	private Random r;
	private double _R;
	private int _variant;
	private boolean[] quarters;

	/* COMMON */
	
	/* WORKAROUND: to get compatibility with original AreaCanvas.java */
	static final Quarter[] qTransTable = new Quarter[] 
		{ Quarter.I, Quarter.IV, Quarter.III, Quarter.II }; 
		
	private Quarter getFreeQuarter()
	{
		int q;
		do q = Math.abs(r.nextInt()) % 4;
		while (quarters[q]);
		
		quarters[q] = true;
		return qTransTable[q];
	}
	
	private QuarterCircle createCircle()
	{
		double radius = 0;
		
		switch(Math.abs(r.nextInt()) % 2)
		{
			case 0: radius = _R; break;
			case 1: radius = _R / 2; break;
		}
		
		Quarter q = getFreeQuarter();
		
		return new QuarterCircle(q, radius);
	}
	
	private QuarterSquare createSquare()
	{
		Quarter q = getFreeQuarter();
		double w = 0, h = 0;
		
		switch(Math.abs(r.nextInt()) % 3)
		{
			case 0:
				w = _R; // R
				h = _R; // R
			break;

			case 1: 
				w = _R / 2; // R/2
				h = _R; // R
			break;

			case 2: 
				w = _R; // R
				h = _R / 2; // R/2
			break;
		}
		
		return new QuarterSquare(q, h, w);
	}
	
	
	private QuarterTriangle createTriangle()
	{
		double w = 0, h = 0;
		
		Quarter q = getFreeQuarter();
		
		w = (Math.abs(r.nextInt()) % 2) == 0 ? _R / 2 : _R; 
		h = (Math.abs(r.nextInt()) % 2) == 0 ? _R / 2 : _R; 
			
		return new QuarterTriangle(q, h, w);
	}
	
	public IArea createArea()
	{
		r.setSeed(_variant);
	
		IArea[] areas = {
			createCircle(),
			createSquare(),
			createTriangle()
		};
		
		return new ComplexArea(areas);
	}
	
	/* LAB 1 POINTS */
	
	private int lab1_nn()
	{
		return (Math.abs(r.nextInt()) % 5) - 2;
	}

	private int lab1_ns()
	{
		return (Math.abs(r.nextInt()) % 3) + 3;
	}
		
	public Point[] generatePointsForLab1()
	{
		Point[] pts = {
			new Point(lab1_ns(), lab1_ns()),
			new Point(lab1_ns(), -lab1_ns()),
			new Point(-lab1_ns(), -lab1_ns()),
			new Point(-lab1_ns(), lab1_ns()),
			new Point(lab1_nn(), lab1_nn())
		};
	
		return pts;
	}
	
	/* LAB 2 COMPONENTS */
	
	

	public GenerationContext(int variant, double R)
	{
		r = new Random(_variant = variant);
		_R = R;
		
		quarters = new boolean[] {false, false, false, false};
	}
}