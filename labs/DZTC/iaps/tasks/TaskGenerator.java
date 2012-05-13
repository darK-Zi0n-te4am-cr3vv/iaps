package DZTC.iaps.tasks;

import DZTC.iaps.core.*;

public class TaskGenerator
{
	
	public static TaskForLab1 Lab1(int variant, double R)
	{
		GenerationContext ctx = new GenerationContext(variant, R);
		return new TaskForLab1(ctx.createArea(), ctx.generatePointsForLab1());
	}
	
	
	public static TaskForLab2 Lab2(int variant, double R)
	{
		GenerationContext ctx = new GenerationContext(variant, R);
		return new TaskForLab2(ctx.createArea(), ctx.generateLab2ChX(), ctx.generateLab2ChY(), ctx.generateLab2ChR());
	}
} 