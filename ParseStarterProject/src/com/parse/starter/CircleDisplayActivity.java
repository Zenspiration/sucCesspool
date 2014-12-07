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
			
			mainAdapter = new ParseQueryAdapter<ParseObject>(this, "Circle");
			mainAdapter.setTextKey("username");
	 
			// Initialize ListView, connect to my todo list, and set initial view to mainAdapter
			listView = (ListView) findViewById(R.id.todo_list_view);
			listView.setAdapter(mainAdapter);
			mainAdapter.loadObjects();
		}
	
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.activity_pool_list, menu);
			return true;
		}
	
		//Posting meals and refreshing the list will be controlled from the Action
		 // Bar.
		 
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
	
