package com.parse.starter;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.app.Activity;
import android.content.Intent;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.GetCallback;
import com.parse.ParseException;

/*CircleExpired activity runs when the countdown timer reaches 0 
* This means the user has not completed all the goals in his/her pool 
by the end of the cycle 

* It displays how much money the user earned during the cycle, and how much money 
he/she has to send to his chosen charity 

*It also sets up a button for the user to try again and create a new pool

*/
public class CircleExpired extends Activity

{
	ParseUser currentUser = ParseUser.getCurrentUser();
	Circle currentCircle; 
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_circle_expired); //sets layout of the page to activity_circle_expired.xml
		
		ParseQuery<Goal> query = ParseQuery.getQuery("Goal");
			query.whereEqualTo("userId", currentUser.getObjectId());
			query.whereEqualTo("archive", false);
			query.findInBackground(new FindCallback<Goal>() {
			public void done(List<Goal> goals, ParseException e) {
				   if (e == null) {
			           int numberOfGoals = goals.size();
					   for (int i = 0; i < numberOfGoals; i++) {
			           goals.get(i).setArchived(true);
			           goals.get(i).saveInBackground();
					   }
				   } 
			}
			
			});
		
		final TextView CircleName = (TextView)findViewById(R.id.inputCircleName);
		final TextView CycleLength = (TextView)findViewById(R.id.inputCycleLength);
		final TextView DollarsCommitted = (TextView)findViewById(R.id.inputMoneyCommitted);
		final TextView Charity = (TextView)findViewById(R.id.inputCharity);
		final TextView DollarsEarned = (TextView)findViewById(R.id.inputDollarsEarned); 
		final TextView DollarsDonated = (TextView)findViewById(R.id.inputDollarsDonated);		
		
    	ParseQuery<Circle> query2 = ParseQuery.getQuery("Circle");	    	
    	query2.whereEqualTo("userId", currentUser.getObjectId());
    	query2.whereEqualTo("archive", false);
    	query2.getFirstInBackground(new GetCallback<Circle>() 
    	{

	    	public void done(Circle circle, ParseException e) 
	    	{
	    		if (e == null) 
	    		{	
	    			currentCircle = circle;
	    			//gets pool name, cycle length, money committed, and charity name from the Circle class
	    			String circleName = currentCircle.getString("name");
    	            int cycleLength = currentCircle.getInt("cycleLength");
    	            int dollarsCommitted = currentCircle.getInt("dollars");
    	            double dollarsEarned = currentCircle.getDouble("dollarsEarned");
    	            String dollarsEarnedRounded = String.format("%.2f", dollarsEarned); //rounds money earned to 2 decimal places
	    	        String charity = currentCircle.getString("charity");
	    	        double dollarsDonated = (double)dollarsCommitted - dollarsEarned;
	    	        String dollarsDonatedRounded = String.format("%.2f", dollarsDonated);//rounds money donated to 2 decimal places
	    	        
	    	        //sets textview of relevant variables in activity_circle_display.xml to what was pulled from the Circle class
	    	     	CircleName.setText(circleName);
	    	     	CycleLength.setText("" + cycleLength);
	    	     	DollarsCommitted.setText("" + dollarsCommitted);
	    	     	DollarsEarned.setText(dollarsEarnedRounded);
	    	     	DollarsDonated.setText(dollarsDonatedRounded);
;	    	     	Charity.setText(charity);

	    		}
	    	}
    	});

	    		//creates the "Dive Into A New Pool" button that links to the front-end layout for the button in activity_circle_expired
	    	    Button buttonCreatePool = (Button)findViewById(R.id.buttonCreatePool);
	    	    //when button is clicked, sets up an intent that takes the user to CreateCircleActivity
	    	    buttonCreatePool.setOnClickListener(new View.OnClickListener() {
	    		    	public void onClick(View v)
	    		    	{
	    		    		currentCircle.setArchived(true);
	    		    		currentCircle.saveInBackground();
	    		    		Intent intent = new Intent(CircleExpired.this, CreateCircleActivity.class);
	    		    		startActivity(intent);
	    		    	}
	    			});
	    		}
	    	}
