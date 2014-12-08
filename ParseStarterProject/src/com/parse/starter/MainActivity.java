package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


/**
 * Activity which starts an intent for either the logged in (MainActivity) or logged out
 * (SignUpOrLoginActivity) activity.
 */

//COMMENT THIS CODE BACK IN AFTER TESTING!!!

/*
public class MainActivity extends Activity {

  public MainActivity() {
  }
  
  @SuppressWarnings("unused")
@Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Check if there is current user info
    ParseUser user= ParseUser.getCurrentUser();
    String userId = user.getObjectId();
    if (user != null) {
      // Start an intent for the logged in activity
    	// Query all the circles that exist
    	ParseQuery<Circle> query = ParseQuery.getQuery("Circle");
    	query.whereEqualTo("userId", userId);
    	//if their user Id exist under a circle, send them to CircleDisplayActivity.java.
    	if (query!=null){
    		startActivity(new Intent(this, CircleDisplayActivity.class));
    	} 
    	//if their user Id does NOT exist under a circle (ie. they don't have a circle) send them to CreateCircleActivity.java
    	else {
    		startActivity(new Intent(this, CreateCircleActivity.class));
    	}
     } 
     else 
     {
      //Start an intent for the logged out activity
      startActivity(new Intent(this, WelcomeActivity.class));
     }
   }
}
*/

import android.app.ListActivity;
import android.widget.Button;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        Button viewPoolsButton = (Button)findViewById(R.id.view_pools_button);
	        viewPoolsButton.setOnClickListener(new View.OnClickListener() 
	        {
	        	public void onClick(View v)
	        	{
	        		Intent intent = new Intent(MainActivity.this, CircleDisplayActivity.class);
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
}



//package com.parse.starter;
//
//import android.app.ListActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.content.Intent;
//
//import com.parse.ParseObject;
//
//public class MainActivity extends ListActivity {
//
//	@Override
//	    protected void onCreate(Bundle savedInstanceState) {
//	        super.onCreate(savedInstanceState);
//	        setContentView(R.layout.activity_main);
//	        
//	        Button viewPoolsButton = (Button)findViewById(R.id.view_pools_button);
//	        viewPoolsButton.setOnClickListener(new View.OnClickListener() 
//	        {
//	        	public void onClick(View v)
//	        	{
//	        		Intent intent = new Intent(MainActivity.this, CircleDisplayActivity.class);
//	        		startActivity(intent);
//	        	}
//	        });
//	        
//	        Button createPoolButton = (Button)findViewById(R.id.create_pool_button);
//	        createPoolButton.setOnClickListener(new View.OnClickListener() 
//	        {
//	        	public void onClick(View v)
//	        	{
//	        		Intent intent = new Intent(MainActivity.this, CreateCircleActivity.class);
//	        		startActivity(intent);
//	        	}
//	        });
//
//	        Button setGoalsButton = (Button)findViewById(R.id.set_goals_button);
//	        setGoalsButton.setOnClickListener(new View.OnClickListener() 
//	        {
//	        	public void onClick(View v)
//	        	{
//	        		Intent intent = new Intent(MainActivity.this, NewGoalActivity.class);
//	        		startActivity(intent);
//	        	}
//	        });
//	        
//	        Button viewGoalsButton = (Button)findViewById(R.id.view_goals_button);
//	        viewGoalsButton.setOnClickListener(new View.OnClickListener() 
//	        {
//	        	public void onClick(View v)
//	        	{
//	        		Intent intent = new Intent(MainActivity.this, GoalListActivity.class);
//	        		startActivity(intent);
//	        	}
//	        });	
//}
//
//	
//	public static void storeGoalObject(){
//		ParseObject goal = new ParseObject("GameScore");
//		goal.put("poolCreater", 1337);
//		goal.put("poolMembers", "Sean Plott");
//		goal.put("amountEachMemberContributes", false);
//		goal.put("amountLeftForTheWeek", 8);
//		goal.saveInBackground();
//	}
//}
//
//
