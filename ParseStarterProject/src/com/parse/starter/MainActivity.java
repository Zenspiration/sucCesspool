package com.parse.starter;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import com.parse.ParseObject;

public class MainActivity extends ListActivity {

	@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        Button viewPoolsButton = (Button)findViewById(R.id.view_pools_button);
	        viewPoolsButton.setOnClickListener(new View.OnClickListener() 
	        {
	        	public void onClick(View v)
	        	{
	        		Intent intent = new Intent(MainActivity.this, PoolOverview.class);
	        		startActivity(intent);
	        	}
	        });
	        
	        Button createPoolButton = (Button)findViewById(R.id.create_pool_button);
	        createPoolButton.setOnClickListener(new View.OnClickListener() 
	        {
	        	public void onClick(View v)
	        	{
	        		Intent intent = new Intent(MainActivity.this, CreateCircleActivity.class);
	        		startActivity(intent);
	        	}
	        });

	        Button setGoalsButton = (Button)findViewById(R.id.set_goals_button);
	        setGoalsButton.setOnClickListener(new View.OnClickListener() 
	        {
	        	public void onClick(View v)
	        	{
	        		Intent intent = new Intent(MainActivity.this, NewGoalActivity.class);
	        		startActivity(intent);
	        	}
	        });
	        
	        Button viewGoalsButton = (Button)findViewById(R.id.view_goals_button);
	        viewGoalsButton.setOnClickListener(new View.OnClickListener() 
	        {
	        	public void onClick(View v)
	        	{
	        		Intent intent = new Intent(MainActivity.this, GoalListActivity.class);
	        		startActivity(intent);
	        	}
	        });	
}

	
	public static void storeGoalObject(){
		ParseObject goal = new ParseObject("GameScore");
		goal.put("poolCreater", 1337);
		goal.put("poolMembers", "Sean Plott");
		goal.put("amountEachMemberContributes", false);
		goal.put("amountLeftForTheWeek", 8);
		goal.saveInBackground();
	}
}


