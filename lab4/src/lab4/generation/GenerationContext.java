package lab4.generation;

import java.util.Random;
import lab4.core.*;

public class GenerationContext 
{
    private Random r;
    private double _R;
    private int _variant;
    private boolean[] quarters;

    /* COMMON */

    /* WORKAROUND: to get compatibility with original AreaCanvas.java */
    static final int[] Q_TABLE = new int[] 
            { 1, 4, 3, 2 }; 

    private int getFreeQuarter()
    {
        int q;
        do q = Math.abs(r.nextInt()) % 4;
        while (quarters[q]);

        quarters[q] = true;
        return Q_TABLE[q];
    }

    private qCircle createCircle()
    {
        double radius = 0;

        switch(Math.abs(r.nextInt()) % 2)
        {
                case 0: radius = _R; break;
                case 1: radius = _R / 2; break;
        }

        int q = getFreeQuarter();

        return new qCircle(q, radius);
    }

    private qRect createSquare()
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

        return new qRect(q, w, h);
    }


    private qTriangle createTriangle()
    {
        double w = 0, h = 0;

        int q = getFreeQuarter();

        w = (Math.abs(r.nextInt()) % 2) == 0 ? _R / 2 : _R; 
        h = (Math.abs(r.nextInt()) % 2) == 0 ? _R / 2 : _R; 

        return new qTriangle(q, w, h);
    }

    public IArea createArea()
    {
        r.setSeed(_variant);

        return new ComplexArea(new IArea[] {
            createCircle(),
            createSquare(),
            createTriangle()
        });
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

    
    
    public GenerationContext(int variant, double R)
    {
        r = new Random(_variant = variant);
        _R = R;

        quarters = new boolean[] {false, false, false, false};
    }
}
