package com.parse.starter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.CountDownTimer;


import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQueryAdapter;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseObject;

import android.content.Intent;
import android.os.CountDownTimer;


import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQueryAdapter;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseObject;

//import android.support.v7.app.ActionBarActivity;

	public class CircleDisplayActivity extends ListActivity //extends ParseQueryAdapter<Circle> 
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
			final TextView inputCircleName = (TextView)findViewById(R.id.inputCircleName);
			final TextView inputCycleLength = (TextView)findViewById(R.id.inputCycleLength);
			final TextView inputDollarsCommitted = (TextView)findViewById(R.id.inputMoneyCommitted);
			final TextView inputCharity = (TextView)findViewById(R.id.inputCharity);
			
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
		    			String circleName = circle.getString("circleName");
	    	            int cycleLength = circle.getInt("cycleLength");
	    	            int dollarsCommitted = circle.getInt("dollars");
		    	        String charity = circle.getString("charity");
		    	        
		    	     	inputCircleName.setText(circleName);
		    	     	inputCycleLength.setText(cycleLength);
		    	     	inputDollarsCommitted.setText(dollarsCommitted);
		    	     	inputCharity.setText(charity);
		    	    } 
	
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

