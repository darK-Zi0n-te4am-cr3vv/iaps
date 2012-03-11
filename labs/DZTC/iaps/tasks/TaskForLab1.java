package DZTC.iaps.tasks;

import DZTC.iaps.core;

public class TaskForLab1 : Task
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
}