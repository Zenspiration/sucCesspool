package com.parse.starter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

public class GoalListActivity extends ListActivity {
	ParseUser currentUser = ParseUser.getCurrentUser();

	private CustomAdapter mainAdapter;
	private ListView listView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goal_list);
		mainAdapter = new CustomAdapter(this);

		listView = (ListView) findViewById(R.id.goal_list);
		listView.setAdapter(mainAdapter);
		mainAdapter.loadObjects();
		listView.setOnItemClickListener((OnItemClickListener) mainAdapter);

	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_goal_list, menu);
		return true;
	}
	

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

	//http://www.michaelevans.org/blog/2013/08/14/tutorial-building-an-android-to-do-list-app-using-parse/
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Goal goal = mainAdapter.getItem(position);
		goal.setCompleted(!goal.isCompleted());
		
		if(goal.isCompleted()) {
			goal.setTitle("Goal Completed!");
		}
		boolean[] goalsCompleted = areAllGoalsCompleted();
		boolean allGoalsCompleted = true;
		for (int i = 0; i < goalsCompleted.length; i++) {
			if (goalsCompleted[i] == false) {
				allGoalsCompleted = false;
			}
			if (allGoalsCompleted) {
				popUp();
			}
			
		}
		updateGoalList();
	}
	
	private void updateGoalList() {
		mainAdapter.loadObjects();
		setListAdapter(mainAdapter);
	}
	
	private 
	
	private boolean[] areAllGoalsCompleted() {
		boolean[] goalCompletion = new boolean[mainAdapter.getCount()];
		for (int i = 0; i< mainAdapter.getCount(); i++) {
			if (mainAdapter.getItem(i).isCompleted()) {
				goalCompletion[i] = true;
			}
		}
		return goalCompletion;
	}
	
	
	private void newGoal() {
		Intent i = new Intent(this, NewGoalActivity.class);
		startActivityForResult(i, 0);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			updateGoalList();
		}
	}

}
