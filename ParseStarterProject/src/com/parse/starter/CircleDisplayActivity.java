//CircleDisplayActivity displays information about the pool the user created
	//pool name, cycle length, money committed, and charity are saved into the Circle class after user inputs them in CreateCircleActivity
	//CircleDisplayActivity pulls this information from the Circle class and displays them on the "View Your Pool" page

package com.parse.starter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import com.parse.GetCallback;
import com.parse.ParseException;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;

	public class CircleDisplayActivity extends Activity
	{ 
		//displays the pool created by the current logged in user
		ParseUser currentUser = ParseUser.getCurrentUser();

		long timePassedInMillis;
			
		public void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_circle_display); //sets layout of the page to activity_circle_display.xml
			
			//learned how to connect front-end and back-end of displayed user input and buttons from Android Development Tutorial
			final TextView inputCircleName = (TextView)findViewById(R.id.inputCircleName);
			final TextView inputCycleLength = (TextView)findViewById(R.id.inputCycleLength);
			final TextView inputDollarsCommitted = (TextView)findViewById(R.id.inputMoneyCommitted);
			final TextView inputCharity = (TextView)findViewById(R.id.inputCharity);
			final TextView timeRemaining= (TextView) findViewById(R.id.timeRemaining);
			
			final Button btnStart = (Button) findViewById(R.id.buttonSetGoals);
		    final Intent serviceIntent = new Intent(CircleDisplayActivity.this, MyService.class);
			
		    
		    //learned how to set up a ParseQuery from Parse Android tutorial
	    	ParseQuery<Circle> query = ParseQuery.getQuery("Circle");	    	
	    	query.whereEqualTo("userId", currentUser.getObjectId());
	    	query.whereEqualTo("archive", false);
	    	query.getFirstInBackground(new GetCallback<Circle>() 
	    	{

		    	public void done(Circle circle, ParseException e) 
		    	{
		    		if (e == null) 
		    		{
		    			//gets pool name, cycle length, money committed, and charity name from the Circle class
		    			String circleName = circle.getString("name");
	    	            int cycleLength = circle.getInt("cycleLength");
	    	            int dollarsCommitted = circle.getInt("dollars");
		    	        String charity = circle.getString("charity");
		    	        
		    	        //sets textview of relevant variables in activity_circle_display.xml to what was pulled from the Circle class
		    	     	inputCircleName.setText(circleName);
		    	     	inputCycleLength.setText("" + cycleLength);
		    	     	inputDollarsCommitted.setText("" + dollarsCommitted);
		    	     	inputCharity.setText(charity);		    		    
		    	     	
		    	     	//sets up a timer
		    	    
		    	     	final int millisecondsInCycle=cycleLength*24*60*60*1000;
				     	
				     	boolean pastTime=true;
				     	int logInYearCopy=LoginActivity.logInYear;
				     	int launchYearCopy=circle.getInt("launchYear");
				     	int logInMonthCopy=LoginActivity.logInMonth;
				     	int launchMonthCopy=circle.getInt("launchMonth");
				     	int logInDayCopy=LoginActivity.logInDay;
				     	int launchDayCopy=circle.getInt("launchDay");
				     	long logInTimeCopy=Calendar.getInstance().getTimeInMillis();
				     	//long logInTimeCopy=LoginActivity.logInTime;
				     	long launchTimeCopy=circle.getLong("launchTime");
				     	long differenceInMillis;
				     	if (logInYearCopy==launchYearCopy&&logInMonthCopy==launchMonthCopy&&launchDayCopy==logInDayCopy){
				     		timePassedInMillis = logInTimeCopy - launchTimeCopy;
				     		
				     	}
				     	if(logInYearCopy==launchYearCopy&&logInMonthCopy==launchMonthCopy&&launchDayCopy<logInDayCopy){
				     		timePassedInMillis = (logInDayCopy-launchDayCopy)*24*3600*1000+(logInTimeCopy-launchTimeCopy);
				     		//millisUntilFinished=millisecondsInCycle-timePassedInMillis;
				     		
				     	}
				     	if(logInYearCopy==launchYearCopy&&launchMonthCopy<logInDayCopy){
				     		timePassedInMillis=(logInMonthCopy-launchMonthCopy)*30*24*3600*1000+(logInDayCopy-launchDayCopy)*24*3600*1000+(logInTimeCopy-launchTimeCopy);
				     		//millisUntilFinished=millisecondsInCycle-timePassedInMillis;
				     		
				     	}
				     	if(logInYearCopy<launchYearCopy){
				     		timePassedInMillis=(logInYearCopy-launchYearCopy)*365*24*3600*1000+(logInMonthCopy-launchMonthCopy)*30*24*3600*1000+(logInDayCopy-launchDayCopy)*24*3600*1000+(logInTimeCopy-launchTimeCopy);
				     		//millisUntilFinished=millisecondsInCycle-timePassedInMillis;
				     		
				     	}
				     	
				     //sets up a timer
				       
				    	CountDownTimer aCounter = new CountDownTimer(millisecondsInCycle-timePassedInMillis, 1000) {
						    public void onTick(long millisUntilFinished) {
//						    	millisUntilFinished=millisecondsInCycle-timePassedInMillis;
						    	int millisUntilFinishedInt= (int) millisUntilFinished;
						    	int hours= millisUntilFinishedInt/3600000;
						    	int minutes= (millisUntilFinishedInt%3600000)/60000;
						    	int seconds= ((millisUntilFinishedInt%3600000)%60000)/1000;
						        timeRemaining.setText(hours+" hours "+minutes+" minutes "+seconds+" seconds");
						        //Calendar rightNow= Calendar.getInstance();
						        
						        
						   }
						    
						  public void onFinish() {
						     timeRemaining.setText("done!");
						   }
						 };
				    	 aCounter.start();
				 
		    	     	//serviceIntent = new Intent(CircleDisplayActivity.this, MyService.class);

		    	     	//btnStart.setOnClickListener(new View.OnClickListener() {
		    	        //});
		    	    }
		    	}
		    	
	    	});
		    	
	
	//creates the "View Your Goals" button that links to the front-end layout for the button in activity_circle_display
    Button buttonViewGoals = (Button)findViewById(R.id.buttonSetGoals);
    //when button is clicked, sets up an intent that takes the user to GoalListActivity
    buttonViewGoals.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v)
	    	{
	    		Intent intent = new Intent(CircleDisplayActivity.this, GoalListActivity.class);
	    		startActivity(intent);
	    		startService(serviceIntent);
	    	}
		});
	}
}

