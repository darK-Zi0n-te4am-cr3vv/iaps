package DZTC.iaps.tasks;

import DZTC.iaps.core.*;

public class TaskForLab1 extends Task
{
	private ComponentDescription _chX;
	private ComponentDescription _chY;
	private ComponentDescription _chR;
	
	
	public ComponentDescription getChX() { return _chX; }
	public ComponentDescription getChY() { return _chY; }
	public ComponentDescription getChR() { return _chR; }
	
	
	public TaskForLab1(IArea area, ComponentDescription chX, ComponentDescription chY, ComponentDescription chR)
	{
		super(area);
		
		_chX = chX;
		_chR = chR;
		_chY = chY;
	}
	
}