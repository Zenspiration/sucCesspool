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

	public class PoolOverview extends ListActivity {
	 
		private ParseQueryAdapter<Pool> mainAdapter;  
		
		@Override
		//List of users & goals 
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			getListView().setClickable(false);
	
			mainAdapter = new ParseQueryAdapter<Pool>(this, Pool.class);
			mainAdapter.setTextKey("title");
			mainAdapter.setTextKey("users");
			mainAdapter.setTextKey("goals");
			mainAdapter.setTextKey("points");
	
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
				updatePoolList();
				break;
			}
	
			}
			return super.onOptionsItemSelected(item);
		}
	
		private void updatePoolList() {
			mainAdapter.loadObjects();
			setListAdapter(mainAdapter);
		}
	
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			if (resultCode == Activity.RESULT_OK) {
				// If a new post has been added, update
				// the list of posts
				updatePoolList();
			}
		}
	
	}
