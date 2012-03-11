// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Areas3.java

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.Random;

class AreaCanvas3 extends Canvas
	implements ActionListener
{

	public AreaCanvas3()
	{
		variant = 0;
		quarters = new int[4];
		r = new Random(variant + 12345);
		setSize(300, 400);
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
		q = Math.abs(r.nextInt()) % 12;
		switch(q)
		{
		case 0: // '\0'
			str = "Button {'1','2','3','4', '5'}";
			break;

		case 1: // '\001'
			str = "Button {'1','1.5','2','2.5','3'}";
			break;

		case 2: // '\002'
			str = "Checkbox {'1','2','3','4', '5'}";
			break;

		case 3: // '\003'
			str = "Checkbox {'1','1.5','2','2.5','3'}";
			break;

		case 4: // '\004'
			str = "Choice {'1','2','3','4', '5'}";
			break;

		case 5: // '\005'
			str = "Choice {'1','1.5','2','2.5','3'}";
			break;

		case 6: // '\006'
			str = "List {'1','2','3','4', '5'}";
			break;

		case 7: // '\007'
			str = "List {'1','1.5','2','2.5','3'}";
			break;

		case 8: // '\b'
			str = "Textfield (2 ... 5)";
			break;

		case 9: // '\t'
			str = "Textfield (1 ... 4)";
			break;

		case 10: // '\n'
			str = "Scrollbar (1 ... 4)";
			break;

		case 11: // '\013'
			str = "Scrollbar (2 ... 5)";
			break;
		}
		return str;
	}

	public String ns()
	{
		q = Math.abs(r.nextInt()) % 24;
		switch(q)
		{
		case 0: // '\0'
			str = "Button {'-4','-3','-2','-1','0','1','2','3','4'}";
			break;

		case 1: // '\001'
			str = "Button {'-2','-1.5','-1','-0.5','0','0.5','1','1.5','2'}";
			break;

		case 2: // '\002'
			str = "Button {'-5','-4','-3','-2','-1','0','1','2','3'}";
			break;

		case 3: // '\003'
			str = "Button {'-3','-2','-1','0','1','2','3','4','5'}";
			break;

		case 4: // '\004'
			str = "Checkbox {'-4','-3','-2','-1','0','1','2','3','4'}";
			break;

		case 5: // '\005'
			str = "Checkbox {'-2','-1.5','-1','-0.5','0','0.5','1','1.5','2'}";
			break;

		case 6: // '\006'
			str = "Checkbox {'-5','-4','-3','-2','-1','0','1','2','3'}";
			break;

		case 7: // '\007'
			str = "Checkbox {'-3','-2','-1','0','1','2','3','4','5'}";
			break;

		case 8: // '\b'
			str = "Choice {'-4','-3','-2','-1','0','1','2','3','4'}";
			break;

		case 9: // '\t'
			str = "Choice {'-2','-1.5','-1','-0.5','0','0.5','1','1.5','2'}";
			break;

		case 10: // '\n'
			str = "Choice {'-5','-4','-3','-2','-1','0','1','2','3'}";
			break;

		case 11: // '\013'
			str = "Choice {'-3','-2','-1','0','1','2','3','4','5'}";
			break;

		case 12: // '\f'
			str = "List {'-4','-3','-2','-1','0','1','2','3','4'}";
			break;

		case 13: // '\r'
			str = "List {'-2','-1.5','-1','-0.5','0','0.5','1','1.5','2'}";
			break;

		case 14: // '\016'
			str = "List {'-5','-4','-3','-2','-1','0','1','2','3'}";
			break;

		case 15: // '\017'
			str = "List {'-3','-2','-1','0','1','2','3','4','5'}";
			break;

		case 16: // '\020'
			str = "Textfield (-3 ... 3)";
			break;

		case 17: // '\021'
			str = "Textfield (-5 ... 5)";
			break;

		case 18: // '\022'
			str = "Textfield (-5 ... 3)";
			break;

		case 19: // '\023'
			str = "Textfield (-3 ... 5)";
			break;

		case 20: // '\024'
			str = "Scrollbar (-3 ... 3)";
			break;

		case 21: // '\025'
			str = "Scrollbar (-5 ... 5)";
			break;

		case 22: // '\026'
			str = "Scrollbar (-5 ... 3)";
			break;

		case 23: // '\027'
			str = "Scrollbar (-3 ... 5)";
			break;
		}
		return str;
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
			String s = "\u0438\u0437\u043C\u0435\u043D\u0435\u043D\u0438\u0435 X: " + ns();
			String s1 = "\u0438\u0437\u043C\u0435\u043D\u0435\u043D\u0438\u0435 Y: " + ns();
			String s2 = "\u0438\u0437\u043C\u0435\u043D\u0435\u043D\u0438\u0435 R: " + nn();
			g.setColor(Color.black);
			g.drawString(s, 10, 240);
			g.drawString(s1, 10, 270);
			g.drawString(s2, 10, 300);
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
	String str;
}
