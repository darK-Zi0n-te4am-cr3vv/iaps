package DZTC.iaps.core;

import java.lang.*;

public enum Quarter
{
	I(1), 
	II(2), 
	III(3), 
	IV(4);
	
	private Quarter(int aint) { _aint = aint; }
	
	private int _aint;
	
	public static Quarter fromInt(int i) 
		throws IllegalArgumentException
	{
		switch (i)
		{
			case 1 : return I;
			case 2 : return II;
			case 3 : return III;
			case 4 : return IV;
			
			default : throw new IllegalArgumentException("invalid quarter");
		}
	}
}