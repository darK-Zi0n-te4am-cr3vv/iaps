package DZTC.iaps.labs.lab1;

/*
	Lab 1, port for TaskGenerator 
*/

import java.io.*;

import DZTC.iaps.core.*;
import DZTC.iaps.tasks.*;


public class Entry
{
	final static int VARIANT = 301;

	static double enterDouble(String Message)	
	{ 
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in)); 
	
		for (;;)	
		{ 
			System.out.print(Message); 
			try	
			{ 
				return Double.parseDouble(bReader.readLine()); 
			}	
			catch (Exception ex)	
			{ 
				System.out.println("Error"); 
			} 
		} 
	} 
	
	private static void actions() 
	{ 
		double R = enterDouble("Enter R: "); 
		TaskForLab1 task = TaskGenerator.Lab1(VARIANT, R);

		Point[] A = task.getPoints();
		IArea S = task.getArea();
	
		for (Point pt : A) 
			if (S.containsPt(pt)) System.out.println(pt); 
	}

	public static void main(String[] args)
	{
		for (;;) actions();
	}
}
