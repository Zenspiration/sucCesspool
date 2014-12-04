package com.parse.starter;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.parse.ParseClassName;
import android.app.Activity;
import android.app.ListActivity;
//import android.support.v7.app.ActionBarActivity;
import android.content.Intent;

	public class CircleDisplayActivity extends ListActivity {
	 
		private ParseQueryAdapter<Circle> mainAdapter;  
		
		@Override
		//List out facts about the pool 
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			getListView().setClickable(false);
	
			mainAdapter = new ParseQueryAdapter<Pool>(this, Circle.class);
			mainAdapter.setTextKey("title");
			mainAdapter.setTextKey("start date");
			mainAdapter.setTextKey("end date");
			mainAdapter.setTextKey("points");
	
			// Default view is all users & goals 
			setListAdapter(mainAdapter);
		}
		//List out facts about each user 
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			getListView().setClickable(false);
	
			mainAdapter = new ParseQueryAdapter<Users>(this, User.class);
			mainAdapter.setTextKey("username");
			mainAdapter.setTextKey("current points");
	
			// Default view is all users & goals 
			setListAdapter(mainAdapter);
		}
	
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.activity_pool_list, menu);
			return true;
		}
	
		/*
		 * Posting meals and refreshing the list will be controlled from the Action
		 * Bar.
		 */
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
