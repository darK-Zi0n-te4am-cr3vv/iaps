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
		int q = getFreeQuarter();
        
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
		int w = 0, h = 0, y = 0, x = 0;
		int q = getQuarter(LINE);
        
		w = 40 * (1 + Math.abs(r.nextInt()) % 2); // R/2 or R
        h = 40 * (1 + Math.abs(r.nextInt()) % 2); // R/2 or R

		p = new Polygon();
		p.addPoint(110, 110); // 0, 0
            
		switch(q)
        {
            case 0: 
                p.addPoint(110 + w, 110); // W, 0
                p.addPoint(110, 110 - h); // 0, -H
            break;

            case 1: 
                p.addPoint(110 + w, 110); // W, 0
                p.addPoint(110, 110 + h); // 0, H
            break;

            case 2: 
                p.addPoint(110 - w, 110); // -W, 0
                p.addPoint(110, 110 + h); // 0, H
            break;

            case 3: 
                p.addPoint(110 - w, 110); // -W, 0
                p.addPoint(110, 110 - h); // 0,  -H
            break;
        }
    
		p.addPoint(110, 110); // 110, 110 is (0; 0) // why it's added 2 times?
        g.fillPolygon(p);
	}
	
	public GenerationContext(int variant, double R)
	{
		r = new Random();
		quarters = new boolean[4];
		_R = R;
	}
}