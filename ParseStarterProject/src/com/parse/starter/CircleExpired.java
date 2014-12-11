package com.parse.starter;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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


public class CircleExpired extends Activity

{
	ParseUser currentUser = ParseUser.getCurrentUser();

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_circle_expired); //sets layout of the page to activity_circle_expired.xml
		
		final TextView inputCircleName = (TextView)findViewById(R.id.inputCircleName);
		final TextView inputCycleLength = (TextView)findViewById(R.id.inputCycleLength);
		final TextView inputDollarsCommitted = (TextView)findViewById(R.id.inputMoneyCommitted);
		final TextView inputCharity = (TextView)findViewById(R.id.inputCharity);
		final TextView timeRemaining= (TextView) findViewById(R.id.timeRemaining);
		
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
	    		}
	    	}
    	});

	    		//creates the "Create a New Pool" button that links to the front-end layout for the button in activity_circle_expired
	    	    Button buttonCreatePool = (Button)findViewById(R.id.buttonSetGoals);
	    	    //when button is clicked, sets up an intent that takes the user to CreateCircleActivity
	    	    buttonCreatePool.setOnClickListener(new View.OnClickListener() {
	    		    	public void onClick(View v)
	    		    	{
	    		    		Intent intent = new Intent(CircleExpired.this, CreateCircleActivity.class);
	    		    		startActivity(intent);
	    		    	}
	    			});
	    		}
	    	}
