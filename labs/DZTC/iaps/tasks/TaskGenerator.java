package DZTC.iaps.tasks;

import DZTC.iaps.core.*;

public class TaskGenerator
{
	
	public static TaskForLab1 Lab1(int variant, double R)
	{
		GenerationContext ctx = new GenerationContext(variant, R);
		return new TaskForLab1(ctx.createArea(), ctx.generatePointsForLab1());
	}
} 