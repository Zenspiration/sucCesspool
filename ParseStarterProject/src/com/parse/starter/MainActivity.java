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
	
	    Button viewPoolsButton = (Button) findViewById(R.id.view_pools_button);
	    viewPoolsButton.setOnClickListener(new OnClickListener() {
	      public void onClick(View v) {
	        // Starts an intent for the sign up activity
	        startActivity(new Intent(MainActivity.this, PoolOverview.class));
	      }	
	}
	
	    Button createNewButton = (Button) findViewById(R.id.create_pool_button);
	    createNewButton.setOnClickListener(new OnClickListener() {
	      public void onClick(View v) {
	        // Starts an intent for the sign up activity
	        startActivity(new Intent(MainActivity.this, CreateCircleActivity.class));
	      }	
	}

	    Button setGoalsButton = (Button) findViewById(R.id.set_goals_button);
	    setGoalsButton.setOnClickListener(new OnClickListener() {
	      public void onClick(View v) {
	        // Starts an intent for the sign up activity
	        startActivity(new Intent(MainActivity.this, NewGoalActivity.class));
	      }	
	}
	
	    Button viewGoalsButton = (Button) findViewById(R.id.view_goals_button);
	    viewGoalsButton.setOnClickListener(new OnClickListener() {
	      public void onClick(View v) {
	        // Starts an intent for the sign up activity
	        startActivity(new Intent(MainActivity.this, GoalListActivity.class));
	      }	
	}


}


