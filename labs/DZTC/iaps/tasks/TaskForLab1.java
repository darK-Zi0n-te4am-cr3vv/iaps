package DZTC.iaps.tasks;

import DZTC.iaps.core.*;

public class TaskForLab1 extends Task
{
	private Point[] _pts;

	public Point[] getPoints()
	{
		return _pts;
	}
	
	public TaskForLab1(IArea area, Point[] pts)
	{
		super(area);
		_pts = pts;
	}
	
	@Override public String toString()
	{
		String s = "";
		for (Point pt : _pts) s += pt.toString() + " ";
	
		return "[lab1: Area = \n" + _area.toString() + "\nPoints = {" + s.trim() + "}]";
	}
}