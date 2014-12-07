package com.parse.starter;

import android.app.Activity;
import android.app.ListActivity;
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
		      // object will be your game score
		    } else {
		      // something went wrong
		    }
		  }
		}); */
				
/*		public View getItemView(Circle circle, View v, ViewGroup parent) 
		{
			super.getItemView(circle, v, parent);
			
		    TextView circleNameTextView = (TextView) v.findViewById(R.id.inputCircleName);
	        circleNameTextView.setText(circle.getCircleName().toString());
	        
	        TextView moneyCommittedTextView = (TextView) v.findViewById(R.id.inputMoneyCommitted);
	        moneyCommittedTextView.setText(circle.getDollarsCommitted());
	        
	        TextView cycleLengthTextView = (TextView) v.findViewById(R.id.inputCycleLength);
	        cycleLengthTextView.setText(circle.getCycleLength());

	        TextView charityTextView = (TextView) v.findViewById(R.id.inputCharity);
	        charityTextView.setText(circle.getCharity().toString());
	        
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


/*			mainAdapter = new ParseQueryAdapter<Circle>(this, Circle.class);
			//mainAdapter = new ParseQueryAdapter<ParseObject>(this, "Todo");

			mainAdapter.setTextKey("circle name");
			mainAdapter.setTextKey("cycle length");
			mainAdapter.setTextKey("money committed");
			mainAdapter.setTextKey("charity");

			setListAdapter(mainAdapter);
			
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
	
			case R.id.action_refresh: {
				updateCircleList();
				break;
			}
	
			}
			return super.onOptionsItemSelected(item);
		}
	*/

	
		private void updateCircleList() {
			mainAdapter.loadObjects();
			setListAdapter(mainAdapter);
		}
	
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			if (resultCode == Activity.RESULT_OK) {
				// If a new post has been added, update
				// the list of posts
				updateCircleList();
			}
		}
}
	
	
