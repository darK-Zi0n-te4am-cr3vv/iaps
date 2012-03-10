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
        setSize(300, 400); /* differs from the AreaCanvas.java from Areas1 */
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
	
	/* DRAW COMPONENTS */
	
	public String nn()
    {
		int q = Math.abs(r.nextInt()) % 12;
		String str = "";
		
        switch(q)
        {
        case 0: // '\0'
            str = "commandButton {'1','2','3','4', '5'}";
            break;

        case 1: // '\001'
            str = "commandButton {'1','1.5','2','2.5','3'}";
            break;

        case 2: // '\002'
            str = "commandLink {'1','2','3','4', '5'}";
            break;

        case 3: // '\003'
            str = "commandLink {'1','1.5','2','2.5','3'}";
            break;

        case 4: // '\004'
            str = "selectOneRadio {'1','2','3','4', '5'}";
            break;

        case 5: // '\005'
            str = "selectOneRadio {'1','1.5','2','2.5','3'}";
            break;

        case 6: // '\006'
            str = "selectOneMenu {'1','2','3','4', '5'}";
            break;

        case 7: // '\007'
            str = "selectOneMenu {'1','1.5','2','2.5','3'}";
            break;

        case 8: // '\b'
            str = "selectBooleanCheckBox {'1','2','3','4', '5'}";
            break;

        case 9: // '\t'
            str = "selectBooleanCheckBox {'1','1.5','2','2.5','3'}";
            break;

        case 10: // '\n'
            str = "inputText (2 ... 5)";
            break;

        case 11: // '\013'
            str = "inputText (1 ... 4)";
            break;
        }
        return str;
    }

    public String ns()
    {
        int q = Math.abs(r.nextInt()) % 24;
		String str = "";
		
        switch(q)
        {
        case 0: // '\0'
            str = "commandButton {'-4','-3','-2','-1','0','1','2','3','4'}";
            break;

        case 1: // '\001'
            str = "commandButton {'-2','-1.5','-1','-0.5','0','0.5','1','1.5','2'}";
            break;

        case 2: // '\002'
            str = "commandButton {'-5','-4','-3','-2','-1','0','1','2','3'}";
            break;

        case 3: // '\003'
            str = "commandButton {'-3','-2','-1','0','1','2','3','4','5'}";
            break;

        case 4: // '\004'
            str = "commandLink {'-4','-3','-2','-1','0','1','2','3','4'}";
            break;

        case 5: // '\005'
            str = "commandLink {'-2','-1.5','-1','-0.5','0','0.5','1','1.5','2'}";
            break;

        case 6: // '\006'
            str = "commandLink {'-5','-4','-3','-2','-1','0','1','2','3'}";
            break;

        case 7: // '\007'
            str = "commandLink {'-3','-2','-1','0','1','2','3','4','5'}";
            break;

        case 8: // '\b'
            str = "selectOneRadio {'-4','-3','-2','-1','0','1','2','3','4'}";
            break;

        case 9: // '\t'
            str = "selectOneRadio {'-2','-1.5','-1','-0.5','0','0.5','1','1.5','2'}";
            break;

        case 10: // '\n'
            str = "selectOneRadio {'-5','-4','-3','-2','-1','0','1','2','3'}";
            break;

        case 11: // '\013'
            str = "selectOneRadio {'-3','-2','-1','0','1','2','3','4','5'}";
            break;

        case 12: // '\f'
            str = "selectOneMenu {'-4','-3','-2','-1','0','1','2','3','4'}";
            break;

        case 13: // '\r'
            str = "selectOneMenu {'-2','-1.5','-1','-0.5','0','0.5','1','1.5','2'}";
            break;

        case 14: // '\016'
            str = "selectOneMenu {'-5','-4','-3','-2','-1','0','1','2','3'}";
            break;

        case 15: // '\017'
            str = "selectOneMenu {'-3','-2','-1','0','1','2','3','4','5'}";
            break;

        case 16: // '\020'
            str = "selectBooleanCheckBox {'-4','-3','-2','-1','0','1','2','3','4'}";
            break;

        case 17: // '\021'
            str = "selectBooleanCheckBox {'-2','-1.5','-1','-0.5','0','0.5','1','1.5','2'}";
            break;

        case 18: // '\022'
            str = "selectBooleanCheckBox {'-5','-4','-3','-2','-1','0','1','2','3'}";
            break;

        case 19: // '\023'
            str = "selectBooleanCheckBox {'-3','-2','-1','0','1','2','3','4','5'}";
            break;

        case 20: // '\024'
            str = "inputText (-3 ... 3)";
            hasText = true;
            break;

        case 21: // '\025'
            str = "inputText (-5 ... 5)";
            hasText = true;
            break;

        case 22: // '\026'
            str = "inputText (-5 ... 3)";
            hasText = true;
            break;

        case 23: // '\027'
            str = "inputText (-3 ... 5)";
            hasText = true;
            break;
        }
        return str;
    }

    public String nst()
    {
		int q = Math.abs(r.nextInt()) % 4;
		String str = "";
        
        switch(q)
        {
        case 0: // '\0'
            str = "inputText (-3 ... 3)";
            break;

        case 1: // '\001'
            str = "inputText (-5 ... 5)";
            break;

        case 2: // '\002'
            str = "inputText (-5 ... 3)";
            break;

        case 3: // '\003'
            str = "inputText (-3 ... 5)";
            break;
        }
        return str;
    }


	private void drawComponents(Graphics g)
	{
		String s = "Changing X: " + ns();
        String s1 = "Changing Y: ";
        if(hasText)
			s1 = s1 + ns();
        else
			s1 = s1 + nst();
        
		String s2 = "Changing R: " + nn();
        g.setColor(Color.black);
        g.drawString(s, 10, 240);
        g.drawString(s1, 10, 270);
        g.drawString(s2, 10, 300);
	}
	
	// The work of this method is based on idea that random with same seed produces same sequences of numbers
	// So we need to be extremely careful modifing this method
	private void drawTask(Graphics g)
	{
		g.setColor(new Color(51, 153, 255));
        r.setSeed(variant);
            
		drawArc(g);
		drawRect(g);
		drawLine(g);
	    
		hasText = false;  /* differs from the AreaCanvas.java from Areas3 */
		drawComponents(g); /* differs from the AreaCanvas.java from Areas1 */
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
	
	boolean hasText ;
}
