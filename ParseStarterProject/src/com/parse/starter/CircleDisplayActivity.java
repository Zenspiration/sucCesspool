package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.app.Service;
import android.os.IBinder;
import android.util.Log;


import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

//import android.support.v7.app.ActionBarActivity;

	public class CircleDisplayActivity extends Activity
	{ 
		ParseUser currentUser = ParseUser.getCurrentUser();

		/*
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Circle");
		String inputCircleName = Circle.getString("circleName");
		double inputMoneyCommitted = Circle.getDouble("dollarsCommitted");
		String inputCycleLength = Circle.getString("cycleLength");
		String inputCharity = Circle.getString("charity");
		*/
	
		/*query.getInBackground("xWMyZ4YEGZ", new GetCallback<ParseObject>() {
		  public void done(ParseObject object, ParseException e) {
		    if (e == null) {
@@ -46,9 +42,8 @@ import com.parse.ParseObject;
		    }
		  }
		}); */
		
		
				
/*		public View getItemView(Circle circle, View v, ViewGroup parent) 
		{
			super.getItemView(circle, v, parent);
			
@@ -66,12 +61,45 @@ import com.parse.ParseObject;
	        
	        return v;			
		}
*/
	

		
		public void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_circle_display);
			
			
			final TextView inputCircleName = (TextView)findViewById(R.id.inputCircleName);
			final TextView inputCycleLength = (TextView)findViewById(R.id.inputCycleLength);
			final TextView inputDollarsCommitted = (TextView)findViewById(R.id.inputMoneyCommitted);
			final TextView inputCharity = (TextView)findViewById(R.id.inputCharity);
			final TextView timeRemaining= (TextView) findViewById(R.id.timeRemaining);
			final TextView inputMoneyGained = (TextView) findViewById(R.id.inputMoneyGained);
			final TextView inputDollarsCommitted2 = (TextView) findViewById(R.id.inputMoneyCommitted2);
			
			final Button btnStart = (Button) findViewById(R.id.buttonSetGoals);
		    final Intent serviceIntent = new Intent(CircleDisplayActivity.this, MyService.class);

			
			
	    	ParseQuery<Circle> query = ParseQuery.getQuery("Circle");	    	
	    	query.whereEqualTo("userId", currentUser.getObjectId());
	    	query.whereEqualTo("archive", false);
	    	query.getFirstInBackground(new GetCallback<Circle>() 
	    	{

		    	public void done(Circle circle, ParseException e) 
		    	{
		    		if (e == null) 
		    		{
		    			String circleName = circle.getString("name");
	    	            int cycleLength = circle.getInt("cycleLength");
	    	            int dollarsCommitted = circle.getInt("dollars");
		    	        String charity = circle.getString("charity");
		    	        //int moneyGained = circle.getInt("moneyGained"); //Jarrell you can add the moneyGained variable into Circle.java
		    	        
		    	     	inputCircleName.setText(circleName);
		    	     	inputCycleLength.setText("" + cycleLength);
		    	     	inputDollarsCommitted.setText("" + dollarsCommitted);
		    	     	inputCharity.setText(charity);
		    	     	//inputMoneyGained.setText("" + moneyGained); //Jarrell you can add the moneyGained variable into Circle.java
		    	     	inputDollarsCommitted2.setText("" + dollarsCommitted);
		    		    
		    	     	
		    	     	//sets up a timer
		    	     	
		    	       /*int millisecondsInCycle=cycleLength*24*60*60*1000;
		    	    	CountDownTimer aCounter = new CountDownTimer(millisecondsInCycle , 1000) {
		    			    public void onTick(long millisUntilFinished) {
		    			    	int millisUntilFinishedInt= (int) millisUntilFinished;
		    			    	int hours= millisUntilFinishedInt/3600000;
		    			    	int minutes= (millisUntilFinishedInt%3600000)/60000;
		    			    	int seconds= ((millisUntilFinishedInt%3600000)%60000)/1000;*/
		    			        timeRemaining.setText(MyService.hours+" hours "+MyService.minutes+" minutes "+MyService.seconds+" seconds");
		    			   // }
		    			    
		    			   // public void onFinish() {
		    			     //  timeRemaining.setText("done!");
		    			    //}
		    			  //};
		    	    	  //aCounter.start();
		    	     	//serviceIntent = new Intent(CircleDisplayActivity.this, MyService.class);

		    	     	btnStart.setOnClickListener(new View.OnClickListener() {

		    	            @Override
		    	            public void onClick(View v) {
		    	                // TODO Auto-generated method stub
		    	                startService(serviceIntent);
		    	            }
		    	        });
		    	    }
	
		    	}
		    	
	    	});
	    	
	
    Button buttonSetGoals = (Button)findViewById(R.id.buttonSetGoals);
    buttonSetGoals.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View v)
    	{
    		Intent intent = new Intent(CircleDisplayActivity.this, GoalListActivity.class);
    		startActivity(intent);
    	}
	});
	}
}

	


/*			mainAdapter = new ParseQueryAdapter<Circle>(this, Circle.class);
			//mainAdapter = new ParseQueryAdapter<ParseObject>(this, "Todo");

			mainAdapter.setTextKey("circle name");
@@ -83,40 +111,15 @@ import com.parse.ParseObject;
			
			//getListView().setClickable(false);
			 			
		} */
			
/*		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.activity_pool_list, menu);
			return true;
		}
			 
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
	
@@ -128,6 +131,8 @@ import com.parse.ParseObject;
			}
			return super.onOptionsItemSelected(item);
		}
	*/

