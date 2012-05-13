package lab2.generation;

import java.util.Random;
import lab2.core.*;
import lab2.ui.controls.*;

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

    /* LAB 2 COMPONENTS */
    
    public IxControl lab2_ns(String name)
    {
        int q = Math.abs(r.nextInt()) % 24;
		
        switch(q)
        {
            case 0: return new xButton(name, new double[] {-4,-3,-2,-1,0,1,2,3,4});
            case 1: return new xButton(name, new double[] {-2,-1.5,-1,-0.5,0,0.5,1,1.5,2});
            case 2: return new xButton(name, new double[] {-5,-4,-3,-2,-1,0,1,2,3});
            case 3: return new xButton(name, new double[] {-3,-2,-1,0,1,2,3,4,5});
            
            case 4: return new xCheckbox(name, new double[] {-4,-3,-2,-1,0,1,2,3,4});
            case 5: return new xCheckbox(name, new double[] {-2,-1.5,-1,-0.5,0,0.5,1,1.5,2});
            case 6: return new xCheckbox(name, new double[] {-5,-4,-3,-2,-1,0,1,2,3});
            case 7: return new xCheckbox(name, new double[] {-3,-2,-1,0,1,2,3,4,5});
            
            case 8: return new xChoice(name, new double[] {-4,-3,-2,-1,0,1,2,3,4});
            case 9: return new xChoice(name, new double[] {-2,-1.5,-1,-0.5,0,0.5,1,1.5,2});
            case 10: return new xChoice(name, new double[] {-5,-4,-3,-2,-1,0,1,2,3});
            case 11: return new xChoice(name, new double[] {-3,-2,-1,0,1,2,3,4,5});
            
            case 12: return new xList(name, new double[] {-4,-3,-2,-1,0,1,2,3,4});
            case 13: return new xList(name, new double[] {-2,-1.5,-1,-0.5,0,0.5,1,1.5,2});
            case 14: return new xList(name, new double[] {-5,-4,-3,-2,-1,0,1,2,3});
            case 15: return new xList(name, new double[] {-3,-2,-1,0,1,2,3,4,5});
            
            case 16: return  new xTextfield(name, -3, 3);
            case 17: return  new xTextfield(name, -5, 5);
            case 18: return  new xTextfield(name, -5, 3);
            case 19: return  new xTextfield(name, -3, 5);

            case 20: return new xScrollbar(name, -3, 3);
            case 21: return new xScrollbar(name, -5, 5);
            case 22: return new xScrollbar(name, -5, 3);
            case 23: return new xScrollbar(name, -3, 5);
            
            default: return  null;
        }
       
    }
    
    public IxControl lab2_nn(String name)
    {
        int q = Math.abs(r.nextInt()) % 12;
		
        switch(q)
        {
            case 0: return new xButton(name, new double[] {1,2,3,4, 5});
            case 1: return new xButton(name, new double[] {1,1.5,2,2.5,3});
            
            case 2: return new xCheckbox(name, new double[] {1,2,3,4, 5});
            case 3: return new xCheckbox(name, new double[]  {1,1.5,2,2.5,3});
            
            case 4: return new xChoice(name, new double[] {1,2,3,4, 5});
            case 5: return new xChoice(name, new double[]{1,1.5,2,2.5,3});
            
            case 6: return new xList(name, new double[] {1,2,3,4,5});
            case 7: return new xList(name, new double[] {1,1.5,2,2.5,3});
           
            case 8: return new xTextfield(name, 2, 5);
            case 9: return new xTextfield(name, 1, 4);

            case 10: return new xScrollbar(name, 1, 4);
            case 11: return new xScrollbar(name, 2, 6);
        
            default: return null;
        }
    }
    
    
    
    public GenerationContext(int variant, double R)
    {
        r = new Random(_variant = variant);
        _R = R;

        quarters = new boolean[] {false, false, false, false};
    }
}
