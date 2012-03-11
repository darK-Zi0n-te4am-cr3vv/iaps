package DZTC.iaps.tests;

import DZTC.iaps.core.*;
import DZTC.iaps.tasks.*;

import java.util.Random;

public class Tests
{
	public static void main(String[] args)
	{
		TaskForLab1 task = TaskGenerator.Lab1(42345, 20);
		System.out.println(task.toString());
		
	}
}
