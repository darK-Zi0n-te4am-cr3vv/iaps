// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Areas.java

// Refactor by C.c (c) 2012

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.Random;

class AreaCanvas extends Canvas
    implements ActionListener
{

    public AreaCanvas()
    {
        variant = 0;
        quarters = new int[4];
        r = new Random(variant);
        setSize(240, 300);
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        try 
		{ 
			variant = Integer.parseInt(((TextField)actionevent.getSource()).getText()); 
		}
		catch(NumberFormatException _ex)
        {
            variant = 0;
        }
		
        repaint();
    }
   
	private int getQuarter(int areaType)
	{
		int q;
		do q = Math.abs(r.nextInt()) % 4;
		while (quarters[q]!=0);
		
		quarters[q] = areaType;
		return q;	
	}
	
	/* DRAW ARC */
	
	private final static int[] __arcSAngles = { 0, -90, 180, 90 };
	private int getArcSAngle(int q)
	{
		return (0 <= q && q < 4) ? __arcSAngles[q] : 0;
	}
	
	private void drawArc(Graphics g)
	{
		int w = 0, h = 0, y = 0, x = 0;
		switch(Math.abs(r.nextInt()) % 2)
        {
            case 0: 
                x = y = 30; // -R 
                w = h = 160; // 2*R
			break;

            case 1:
                x = y = 70; // -R/2
                w = h = 80; // R
			break;
        }
    	
		// Selecting a quater for an arc 
		int q = getQuarter(CIRCLE);
		int sAngle = getArcSAngle(q);
       
        g.fillArc(x, y, w, h, sAngle, 90);    
	}
	
	/* DRAW RECT */
	
	private void drawRect(Graphics g)
	{
		int w = 0, h = 0, y = 0, x = 0;
		int q = getQuarter(SQUARE);
        
		switch(Math.abs(r.nextInt()) % 3)
        {
            case 0:
                w = 80; // R
                h = 80; // R
            break;

            case 1: 
                w = 40; // R/2
                h = 80; // R
            break;

            case 2: 
                w = 80; // R
                h = 40; // R/2
            break;
        }
        
		switch(q)
        {
			case 0: 
                x = 110; // 0
                y = 110 - h; // -H
            break;

            case 1: 
                x = 110; // 0
                y = 110; // 0
            break;

            case 2: 
                x = 110 - w; // -W
                y = 110; // 0
            break;

            case 3: 
                x = 110 - w; // -W
                y = 110 - h; // -H
            break;
        }
        
		g.fillRect(x, y, w, h);
	}
	
	/* DRAW LINE */
	
	private void drawLine(Graphics g)
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
	
	/* POINT LIST */
	
	public String nn()
    {
        return String.valueOf(Math.abs(r.nextInt()) % 5 - 2).trim();
    }

    public String ns()
    {
        return String.valueOf(Math.abs(r.nextInt()) % 3 + 3).trim();
    }
	
	private void drawPointList(Graphics g)
	{
		StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("A = {");
        stringbuffer.append("{" + ns() + "," + ns() + "}, ");
        stringbuffer.append("{" + ns() + ",-" + ns() + "}, ");
        stringbuffer.append("{-" + ns() + ",-" + ns() + "}, ");
        stringbuffer.append("{-" + ns() + "," + ns() + "}, ");
        stringbuffer.append("{" + nn() + "," + nn() + "}}");
            
		g.setColor(Color.black);
        g.drawString(stringbuffer.toString(), 10, 220);
	}
	
	
	// The work of this method is based on idea that random with same seed produces same sequences of numbers
	// So we need to be extremely careful modifing this method
	private void drawTask(Graphics g)
	{
		g.setColor(new Color(51, 153, 255));
        r.setSeed(variant);
		g.drawString(Integer.toString(variant), 10, 210);
            
		drawArc(g);
		drawRect(g);
		drawLine(g);
	    
		drawPointList(g);
		
	}
	
	private void drawAxis(Graphics g)
	{
		g.setColor(Color.black);
		
        g.drawLine(10, 110, 210, 110);
        g.drawLine(110, 210, 110, 10);
        g.drawLine(205, 108, 210, 110);
        g.drawLine(205, 112, 210, 110);
        g.drawLine(108, 15, 110, 10);
        g.drawLine(112, 15, 110, 10);
        
		g.drawString("x", 205, 105);
        g.drawString("y", 115, 15);
        
		g.drawLine(109, 30, 111, 30); // -R
        g.drawLine(109, 190, 111, 190); // +R
        g.drawLine(30, 109, 30, 111);
        g.drawLine(190, 109, 190, 111);
        
		g.drawString("R", 115, 35);
        g.drawString("-R", 115, 195);
        g.drawString("-R", 25, 105);
        g.drawString("R", 185, 105);
        
		g.drawLine(109, 70, 111, 70); // -R/2
        g.drawLine(109, 150, 111, 150); // +R/2
        g.drawLine(70, 109, 70, 111);
        g.drawLine(150, 109, 150, 111);
        
		g.drawString("R/2", 115, 75);
        g.drawString("-R/2", 115, 155);
        g.drawString("-R/2", 65, 105);
        g.drawString("R/2", 145, 105);
		
		// So, R = 80
	}

	public void paint(Graphics g)
    {
        super.paint(g);

        for (int i = 0; i < 4; i++) quarters[i] = 0;

        if(variant != 0) drawTask(g);
		drawAxis(g);
	}
	
	
    static final int CIRCLE = 1;
    static final int SQUARE = 2;
    static final int LINE = 3;
	
    int variant;
    int quarters[];
   
    Polygon p;
    Random r;
}
