package com.parse.starter;

import com.parse.ParseUser;

public class User {

	String userName; 
	String[] goals; 
	int currentPoints; 
	int totalPointsInCycle; 
	boolean completedAllGoals;

	public static void main (String[] args)
	{
		System.out.println("hello world");
	}
	
	//code to see if the user completed 100% of their goals 
	public static boolean completedAllGoals(User x)
	{
		for (int i = 0; i < x.goals.length; i++)
		{
			if (x.goals[i].equals("completed")) //should we make whether EACH goal is completed into a boolean array? (in addition to whether all goals are completed) -Lucia
			{
				System.out.println("You have completed all your goals!");
				return true; 
			}
			else {
				return false;
			}
		}
		
		return false;
	}
	
	public static void rewardUser(User x)
	{
		if (completedAllGoals(x))
		
		{
			System.out.println("CONGRATULATIONS"); //ADD IN CODE: you have earned "X" dollars back today! -Lucia
			//maybe program in other rewards like a gif, if we have time 
		}
	}
	
	
	
	/*  
	 *  Put this section of code into another file for an example set-up w/o user input 
	 *  
		User devin = new User(); 
		devin.userName = new String("devinheaven26");  
		
		devin.goals = new String[4]; 
		devin.goals[0] = new String("Finish paper for Bioethics seminar"); 
		devin.goals[1] = new String("Ask that guy out on a date");   
		devin.goals[2] = new String("Call grandma");
		devin.goals[3] = new String("Programming homework");
		
		devin.totalPointsInCycle = 21; 
		//devin.currentPoints = function of how many goals completed so far  
		devin.completedAllGoals = false;
		
		*/
	
}
