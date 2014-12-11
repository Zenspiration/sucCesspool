package com.parse.starter;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/* MainActivity functions as a Dispatch activity. 
 * We programmed our app such that each user is only allowed to have one pool 
 * at a time. When they login, we execute a ParseQuery for circles associated with 
 * their userId. If a circle exists (i.e. the user already created one), user is sent straight
 * to CircleDisplayActivity on login. Otherwise, the user is sent to CreateCircleActivity
 * where he/she can set up a new pool. 
 */
public class MainActivity extends Activity {
	
    ParseUser currentUser = ParseUser.getCurrentUser();
    

	@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        ParseQuery<Circle> query = ParseQuery.getQuery("Circle");	    	
	    	query.whereEqualTo("userId", currentUser.getObjectId());
	    	query.whereEqualTo("archive", false);
	    	query.getFirstInBackground(new GetCallback<Circle>() 
	    	{
		    	public void done(Circle circle, ParseException e) 
		    	{
		    		if (e == null) 
		    		{
		    			Intent intent = new Intent(MainActivity.this, CircleDisplayActivity.class);
		    			startActivity(intent);
		    		}
		    		else 
		    		{
		    			Intent intent = new Intent(MainActivity.this, CreateCircleActivity.class);
		    			startActivity(intent);
		    		}
		    
		    	}
	    	});
	}
}
/*For development purposes, we originally coded a "fake" MainActivity with buttons that led to all our
 * individual pages/activities. This was to ensure that, should anything be wrong with a particular
 * intent/activity, we had a means of bypassing this and accessing all other pages for testing and development
 
	        
	        
	        //sets up a button that takes the user to CircleDisplayActivity when clicked
	        Button viewPoolsButton = (Button)findViewById(R.id.view_pools_button);
	        viewPoolsButton.setOnClickListener(new View.OnClickListener() 
	        {
	        	public void onClick(View v)
	        	{
	        		Intent intent = new Intent(MainActivity.this, CircleDisplayActivity.class);
	        		startActivity(intent);
	        	}
	        });

	        //sets up a button that takes the user to CreateCircleActivity when clicked
	        Button createPoolButton = (Button)findViewById(R.id.create_pool_button);
	        createPoolButton.setOnClickListener(new View.OnClickListener() 
	        {
	        	public void onClick(View v)
        	{
	        		Intent intent = new Intent(MainActivity.this, CreateCircleActivity.class);
	        		startActivity(intent);
	        	}
	        });

	        //sets up a button that takes the user to NewGoalActivity when clicked
	        Button setGoalsButton = (Button)findViewById(R.id.set_goals_button);
	        setGoalsButton.setOnClickListener(new View.OnClickListener() 
	        {
	        	public void onClick(View v)
	        	{
	        		Intent intent = new Intent(MainActivity.this, NewGoalActivity.class);
	        		startActivity(intent);
	        	}
	        });

	        //sets up a button that takes the user to GoalListActivity when clicked
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

*/