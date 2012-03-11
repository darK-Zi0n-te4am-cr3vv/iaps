// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Areas.java

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

	public String nn()
	{
		return String.valueOf(Math.abs(r.nextInt()) % 5 - 2).trim();
	}

	public String ns()
	{
		return String.valueOf(Math.abs(r.nextInt()) % 3 + 3).trim();
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		quarters[0] = 0;
		quarters[1] = 0;
		quarters[2] = 0;
		quarters[3] = 0;
		if(variant != 0)
		{
			g.setColor(new Color(51, 153, 255));
			r.setSeed(variant);
			switch(Math.abs(r.nextInt()) % 2)
			{
			case 0: // '\0'
				x = y = 30;
				w = h = 160;
				break;

			case 1: // '\001'
				x = y = 70;
				w = h = 80;
				break;
			}
			q = Math.abs(r.nextInt()) % 4;
			quarters[q] = 1;
			switch(q)
			{
			case 0: // '\0'
				sAngle = 0;
				break;

			case 1: // '\001'
				sAngle = -90;
				break;

			case 2: // '\002'
				sAngle = 180;
				break;

			case 3: // '\003'
				sAngle = 90;
				break;
			}
			g.fillArc(x, y, w, h, sAngle, 90);
			for(; quarters[q] != 0; q = Math.abs(r.nextInt()) % 4);
			quarters[q] = 2;
			switch(Math.abs(r.nextInt()) % 3)
			{
			case 0: // '\0'
				w = 80;
				h = 80;
				break;

			case 1: // '\001'
				w = 40;
				h = 80;
				break;

			case 2: // '\002'
				w = 80;
				h = 40;
				break;
			}
			switch(q)
			{
			case 0: // '\0'
				x = 110;
				y = 110 - h;
				break;

			case 1: // '\001'
				x = 110;
				y = 110;
				break;

			case 2: // '\002'
				x = 110 - w;
				y = 110;
				break;

			case 3: // '\003'
				x = 110 - w;
				y = 110 - h;
				break;
			}
			g.fillRect(x, y, w, h);
			for(; quarters[q] != 0; q = Math.abs(r.nextInt()) % 4);
			quarters[q] = 3;
			w = 40 * (1 + Math.abs(r.nextInt()) % 2);
			h = 40 * (1 + Math.abs(r.nextInt()) % 2);
			p = new Polygon();
			p.addPoint(110, 110);
			switch(q)
			{
			case 0: // '\0'
				p.addPoint(110 + w, 110);
				p.addPoint(110, 110 - h);
				break;

			case 1: // '\001'
				p.addPoint(110 + w, 110);
				p.addPoint(110, 110 + h);
				break;

			case 2: // '\002'
				p.addPoint(110 - w, 110);
				p.addPoint(110, 110 + h);
				break;

			case 3: // '\003'
				p.addPoint(110 - w, 110);
				p.addPoint(110, 110 - h);
				break;
			}
			p.addPoint(110, 110);
			g.fillPolygon(p);
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
		g.setColor(Color.black);
		g.drawLine(10, 110, 210, 110);
		g.drawLine(110, 210, 110, 10);
		g.drawLine(205, 108, 210, 110);
		g.drawLine(205, 112, 210, 110);
		g.drawLine(108, 15, 110, 10);
		g.drawLine(112, 15, 110, 10);
		g.drawString("x", 205, 105);
		g.drawString("y", 115, 15);
		g.drawLine(109, 30, 111, 30);
		g.drawLine(109, 190, 111, 190);
		g.drawLine(30, 109, 30, 111);
		g.drawLine(190, 109, 190, 111);
		g.drawString("R", 115, 35);
		g.drawString("-R", 115, 195);
		g.drawString("-R", 25, 105);
		g.drawString("R", 185, 105);
		g.drawLine(109, 70, 111, 70);
		g.drawLine(109, 150, 111, 150);
		g.drawLine(70, 109, 70, 111);
		g.drawLine(150, 109, 150, 111);
		g.drawString("R/2", 115, 75);
		g.drawString("-R/2", 115, 155);
		g.drawString("-R/2", 65, 105);
		g.drawString("R/2", 145, 105);
	}

	static final int CIRCLE = 1;
	static final int SQUARE = 2;
	static final int LINE = 3;
	int variant;
	int quarters[];
	int q;
	int sAngle;
	int x;
	int y;
	int w;
	int h;
	Polygon p;
	Random r;
}
