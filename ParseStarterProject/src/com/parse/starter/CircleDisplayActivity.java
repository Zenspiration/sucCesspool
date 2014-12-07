package com.parse.starter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
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



	public class CircleDisplayActivity //extends ParseQueryAdapter<Circle> 
	{ 
		ParseUser currentUser = ParseUser.getCurrentUser();

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Circle");
		String inputCircleName = Circle.getString("score");
		double inputMoneyCommitted = Circle.getDouble("playerName");
		String inputCycleLength = Circle.getString("score");
		String inputCharity = Circle.getString("score");

		
		/*query.getInBackground("xWMyZ4YEGZ", new GetCallback<ParseObject>() {
		  public void done(ParseObject object, ParseException e) {
		    if (e == null) {
		      // object will be your game score
		    } else {
		      // something went wrong
		    }
		  }
		}); */
		
		
		public View getItemView(Circle circle, View v, ViewGroup parent) 
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
		
		public void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			
			mainAdapter = new ParseQueryAdapter<Circle>(this, Circle.class);
			//mainAdapter = new ParseQueryAdapter<ParseObject>(this, "Todo");

			mainAdapter.setTextKey("circle name");
			mainAdapter.setTextKey("cycle length");
			mainAdapter.setTextKey("money committed");
			mainAdapter.setTextKey("charity");

			setListAdapter(mainAdapter);
			
			//getListView().setClickable(false);
			 			
		}
	
			private ParseQueryAdapter<Circle> mainAdapter; 
			{
				
			}
			
			
//			// Default view is all users & goals 
//			setListAdapter(mainAdapter);
//		}
//		//List out facts about each user 
//		public void onCreate(Bundle savedInstanceState) {
//			super.onCreate(savedInstanceState);
//			getListView().setClickable(false);
//	
//			mainAdapter = new ParseQueryAdapter<Users>(this, User.class);
//			mainAdapter.setTextKey("username");
//			mainAdapter.setTextKey("current points");
//	
//			// Default view is all users & goals 
//			setListAdapter(mainAdapter);
//		}
	
		@Override
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
	
