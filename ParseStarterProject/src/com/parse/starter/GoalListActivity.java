package com.parse.starter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseQueryAdapter;

public class GoalListActivity extends ListActivity {

	private ParseQueryAdapter<Goal> mainAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getListView().setClickable(false);

		mainAdapter = new ParseQueryAdapter<Goal>(this, Goal.class);
		mainAdapter.setTextKey("Your goals for today!");

		setListAdapter(mainAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_goal_list, menu);
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
			updateGoalList();
			break;
		}

		case R.id.action_new: {
			newGoal();
			break;
		}
		}
		return super.onOptionsItemSelected(item);
	}

	private void updateGoalList() {
		mainAdapter.loadObjects();
		setListAdapter(mainAdapter);
	}


	private void newGoal() {
		Intent i = new Intent(this, NewGoalActivity.class);
		startActivityForResult(i, 0);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			// If a new post has been added, update
			// the list of posts
			updateGoalList();
		}
	}

}
