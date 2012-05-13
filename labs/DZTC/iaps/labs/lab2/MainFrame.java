package DZTC.iaps.labs.lab2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


import DZTC.iaps.core.*;



public class MainFrame extends JFrame
{
	private double _R = 1; 
	
	private GraphicsCanvas _canvas = new GraphicsCanvas();
	public GraphicsCanvas getCanvas() { return _canvas; }
	
	private JPanel _controlsPanel = new JPanel();
	public JPanel getControlsPanel() { return _controlsPanel; }

    /* Event handlers */

	private void onXChanged(double newX)
	{}
	
	private void onYChanged(double newX)
	{}
	
	private void onRChanged(double newX)
	{}

    private void onCanvasMouseClick(MouseEvent me)
    {}

    private void onMainWindowClosing(WindowEvent we)
    {}
	
	private void initSpecialComponentX()
	{
		Scrollbar xScrollbar = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, -5, 4);
        //xScrollbar.addAdjustmentListener(new  );
        getControlsPanel().add(xScrollbar);
	}
	
	private void initComponents()
	{
		setLayout(new BorderLayout());
		setSize(Const.WINDOW_SIZE_X, Const.WINDOW_SIZE_Y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowListener {
             public void windowClosing(WindowEvent we){ onMainWindowClosing(we); }
        });
        setResizable(false);    
		
		/* canvas */
		getCanvas().addMouseListener(new Canvas_OnMouseClick());
		add(getCanvas(), BorderLayout.CENTER);
		
		/* panel */
        add(getControlsPanel(), BorderLayout.SOUTH);
		Label xLabel = new Label("X = ");
        getControlsPanel().add(xLabel);
		
        
	}
	
    public MainFrame()
	{
		initComponents();


        


        Label yLabel = new Label("Y =");
        jp.add(yLabel);

        yList = new List(2);
        for (double j = 2; j >= -2; j -= 0.5) {
           yList.add(Double.toString(j));
        }
        yList.addActionListener(this);
        jp.add(yList);

        Label rLabel = new Label("R =");
        jp.add(rLabel);

        rChoice = new Choice();        
        for (int i = 1; i <= 5; i++) {
            rChoice.add(Integer.toString(i));
        }
        rChoice.addItemListener(this);
        jp.add(rChoice);

        setVisible(true);
    }

    public void paint(Graphics g) {
        if (gc.getGraphics() != null) {
            gc.repaint();
        }
    }

    /*
     * ����� ��� ������ ������� � ����� �� �����
     */
    private void drawArea(Graphics g) {
        double x;
            int i;

            /* �������� ���� */
            g.setColor(Color.white);
            g.fillRect(0, 0, AREA_SIZE_X, AREA_SIZE_Y);

            /* ������ ������ */
            g.setColor(Color.CYAN);
            g.fillArc(HALF_X - 2 * STEP, HALF_Y - 2 * STEP,
                    4 * STEP, 4 * STEP, 0, 90);
            g.fillPolygon(xPoints, yPoints, xPoints.length);

            /* ������ ��� */
            g.setColor(Color.black);
            g.drawLine(HALF_X - LENGTH_X, HALF_Y, HALF_X + LENGTH_X, HALF_Y);
            g.drawLine(HALF_X, HALF_Y - LENGTH_Y, HALF_X, HALF_Y + LENGTH_Y);

            /* ������ ��������� � ���� */
            g.drawLine(HALF_X, HALF_Y - LENGTH_Y,
                    HALF_X - INDENT / 2, HALF_Y - LENGTH_Y + INDENT);
            g.drawLine(HALF_X, HALF_Y - LENGTH_Y,
                    HALF_X + INDENT / 2, HALF_Y - LENGTH_Y + INDENT);
            g.drawLine(HALF_X + LENGTH_X, HALF_Y,
                    HALF_X + LENGTH_X - INDENT, HALF_Y - INDENT / 2);
            g.drawLine(HALF_X + LENGTH_X, HALF_Y,
                    HALF_X + LENGTH_X - INDENT, HALF_Y + INDENT / 2);

            /* ������ ������� �� ���� */
            for (i = -4; i <= 4; i++) {
                g.drawLine(HALF_X - INDENT / 2, HALF_Y + i * STEP,
                        HALF_X + INDENT / 2, HALF_Y + i * STEP);
            }
            for (i = -10; i<= 10; i++) {
                g.drawLine(HALF_X + i * STEP, HALF_Y - INDENT / 2,
                        HALF_X + i * STEP, HALF_Y + INDENT / 2);
            }

            /* ����� �������� */
            g.drawString("Y", HALF_X + INDENT, HALF_Y - LENGTH_Y + INDENT);
            g.drawString("X", HALF_X + LENGTH_X - INDENT, HALF_Y - INDENT);
            for (x = -5 * R, i = -10; i <= 10; i++, x += R/2) {
                if (i == 0) continue;
                g.drawString(Double.toString(x), HALF_X + i * STEP - INDENT,
                        HALF_Y - INDENT);
                if ((i >= -4) && (i <= 4)) {
                    g.drawString(Double.toString(x), HALF_X + INDENT,
                        HALF_Y - i * STEP + INDENT / 2);
                }
            }

            /* ����� ����� �� ������ */
            if (point.isInArea(R)) g.setColor(Color.yellow);
            else g.setColor(Color.red);
            g.fillOval(xPoint - 2, yPoint - 2, 4, 4);
            g.setColor(Color.black);
            g.drawString(point.toString(), INDENT, HALF_Y + LENGTH_Y + INDENT);
            g.drawString(fileMessage, HALF_X + INDENT, HALF_Y + LENGTH_Y + INDENT);
        }       

    /*
     * ����� ��� ��������� �������������� ��������
     */
    public void adjustmentValueChanged(AdjustmentEvent ae) {
        int pixelsPerUnit = (int) Math.round(2 * STEP / R);
        int tempX         = ae.getValue();
        point.setPoint(tempX, point.getY());
        xPoint = HALF_X + pixelsPerUnit * tempX;
        if (!fileNotOpen)  {
            pwFile.print(point);
            if  (point.isInArea(R)) {
                pwFile.println(msgInArea);
            } else {
                pwFile.println(msgNotInArea);
            }
            pwFile.flush();
        }
        repaint();
    }

    public void actionPerformed(ActionEvent ae) {
        int    pixelsPerUnit = (int) Math.round(2 * STEP / R);
        double tempY         = Double.parseDouble(ae.getActionCommand());
        point.setPoint(point.getX(), tempY);
        yPoint = HALF_Y - (int) Math.round(pixelsPerUnit * tempY);
        if (!fileNotOpen)  {
            pwFile.print(point);
            if  (point.isInArea(R)) {
                pwFile.println(msgInArea);
            } else {
                pwFile.println(msgNotInArea);
            }
            pwFile.flush();
        }
        repaint();
    }

    public void itemStateChanged(ItemEvent ie) {
        int pixelsPerUnit;

        R = Double.parseDouble((String) ie.getItem());
        pixelsPerUnit =  (int) Math.round(2 * STEP / R);
        xPoint = HALF_X + (int) Math.round(point.getX() * pixelsPerUnit);
        yPoint = HALF_Y - (int) Math.round(point.getY() * pixelsPerUnit);
        repaint();
    }
	


	
    class GraphicsCanvas extends Canvas {
        private final int AREA_SIZE_X = 500;
        private final int AREA_SIZE_Y = 400;

        public GraphicsCanvas() {
            this.setSize(AREA_SIZE_X, AREA_SIZE_Y);
        }

        public void paint(Graphics g) {
            drawArea(g);
        }
    }
    

   
   
}
