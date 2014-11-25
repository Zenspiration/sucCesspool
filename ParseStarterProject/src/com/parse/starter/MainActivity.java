package com.parse.starter;

import com.parse.ParseObject;

public class MainActivity {
	
	public static void storeGoalObject(){
		ParseObject goal = new ParseObject("GameScore");
		goal.put("poolCreater", 1337);
		goal.put("poolMembers", "Sean Plott");
		goal.put("amountEachMemberContributes", false);
		goal.put("amountLeftForTheWeek", 8);
		goal.saveInBackground();
	}

}


