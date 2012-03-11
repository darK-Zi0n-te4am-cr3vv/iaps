package DZTC.iaps.tasks;

import DZTC.iaps.core.*;

public class Task
{
	protected IArea _area;
	
	public IArea getArea()
	{
		return _area;
	}
	
	public Task(IArea area)
	{
		_area = area;
	}
	
}