package DZTC.iaps.tasks;

import java.util.Random;
import DZTC.iaps.core.*;

class GenerationContext
{
	private Random r;
	private double _R;
	private boolean[] quarters;

	private Quarter getFreeQuarter()
	{
		int q;
		do q = Math.abs(r.nextInt()) % 4;
		while (quarters[q]);
		
		return Quarter.fromInt(q + 1);	
	}
	
	private QuarterCircle createCircle()
	{
		double radius = 0;
		
		switch(Math.abs(r.nextInt()) % 2)
        {
            case 0: radius = _R; break;
            case 1: radius = _R / 2; break;
        }
    	
		return new QuarterCircle(getFreeQuarter(), radius);
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
		IArea[] areas = {
			createCircle(),
			createSquare(),
			createTriangle()
		};
		
		return new ComplexArea(areas);
	}
	
	public GenerationContext(int variant, double R)
	{
		r = new Random();
		quarters = new boolean[4];
		_R = R;
	}
}