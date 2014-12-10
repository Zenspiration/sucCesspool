package com.parse.starter;

import java.util.List;

import android.R.color;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

public class GoalListActivity extends ListActivity {
	ParseUser currentUser = ParseUser.getCurrentUser();
	private CustomAdapter mainAdapter;
	private ListView listView;
	int dollarsCommitted;
	public View row;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goal_list);
		mainAdapter = new CustomAdapter(this);
		mainAdapter.setTextKey("title");

		listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(mainAdapter);
		mainAdapter.loadObjects();
		
		 ParseQuery<Circle> query = ParseQuery.getQuery("Circle");
			query.whereEqualTo("userId", currentUser.getObjectId());
			query.whereEqualTo("archive", false);
			query.getFirstInBackground(new GetCallback<Circle>() {
			public void done(Circle circle, ParseException e) {
				// TODO Auto-generated method stub
				   if (e == null) {
			            dollarsCommitted = circle.getInt("dollars");
				   }
			
			if(mainAdapter.getCount() == 0) {
			TextView dollarsearned = (TextView) findViewById(R.id.goal_moneyearned);
			dollarsearned.setText("You have not earned any money back yet");
			}
			else {
				updateDollarsEarned();
			}
			
		listView.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Goal goal = mainAdapter.getItem(position);
			goal.setCompleted(true);
			goal.saveInBackground();
			updateDollarsEarned();
			
			 if (row != null) {
			        row.setBackgroundResource(android.R.color.holo_green_light);
			    }
			    row = view;
			    view.setBackgroundResource(android.R.color.holo_green_light);
		
			boolean[] goalsCompleted = areAllGoalsCompleted();
			boolean allGoalsCompleted = true;
			for (int i = 0; i < goalsCompleted.length; i++) {
				if (goalsCompleted[i] == false) {
					allGoalsCompleted = false;
				}
			}
			if (allGoalsCompleted) {
				popUp();
			}

		}
		});
	}
			});
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
			updateDollarsEarned();
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
	
	private void updateGoalList() {
		mainAdapter.loadObjects();
		setListAdapter(mainAdapter);
	}

	private void popUp() {
		LayoutInflater layoutInflater 
	     = (LayoutInflater)getBaseContext()
	      .getSystemService(LAYOUT_INFLATER_SERVICE);  
	    final View popupView = layoutInflater.inflate(R.layout.popup,
	    		(ViewGroup) findViewById(R.id.popup_element));  
	             final PopupWindow popupWindow = new PopupWindow(
	               popupView, 
	               300, 370, true);
	             popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
	             
	                  	         
				             /*ParseQuery<Circle> query = ParseQuery.getQuery("Circle");
					 			query.whereEqualTo("userId", currentUser.getObjectId());
					 			query.getFirstInBackground(new GetCallback<Circle>() {
					 			public void done(Circle circle, ParseException e) {
					 				// TODO Auto-generated method stub
					 				   if (e == null) {
					 			            int dollarsCommitted = circle.getInt("dollars");
					 			            */
					 			            String dollarsCommittedText = ("" + dollarsCommitted);
					 			           TextView message = (TextView) popupView.findViewById (R.id.goals_completed);
 
					 			            message.setText("Congratulations! You have completed your goals for this cycle and earned back $" + dollarsCommittedText);
					 			        		  
					 				   
					 	
				           
				    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

						@Override
						public void onDismiss() {
							archiveCompletedGoals();// TODO Auto-generated method stub		
							updateGoalList();
							ParseQuery<Circle> query = ParseQuery.getQuery("Circle");
					 			query.whereEqualTo("userId", currentUser.getObjectId());
					 			query.whereEqualTo("archive", false);
					 			query.getFirstInBackground(new GetCallback<Circle>() {
					 			public void done(Circle circle, ParseException e) {
					 				// TODO Auto-generated method stub
					 				   if (e == null) {
					 			            circle.setArchived(true); 
					 			            circle.saveInBackground();
					 				   } 
					 			}
					 		});
					 		Intent intent = new Intent(GoalListActivity.this, CreateCircleActivity.class);
					 		startActivity(intent);
							
						}
				    	
				    });
					        
				                
				             Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
				             btnDismiss.setOnClickListener(new Button.OnClickListener(){

				     @Override
				     public void onClick(View v) {				      
				      popupWindow.dismiss();
				     }});
			
	
				
			}
		

	private void updateDollarsEarned() {
		double dollarsPerGoal = (double)dollarsCommitted / (double)mainAdapter.getCount();
		double dollarsEarnedBack = 0;
		for (int i = 0; i< mainAdapter.getCount(); i++) {
			if (mainAdapter.getItem(i).isCompleted()) {
				dollarsEarnedBack = dollarsEarnedBack + dollarsPerGoal;
			}
		}
		String dollarsEarnedBackText = String.format("%.2f", dollarsEarnedBack);
		String dollarsPerGoalText = String.format("%.2f", dollarsPerGoal);
		TextView dollarsearned = (TextView) findViewById(R.id.goal_moneyearned);
		dollarsearned.setText("Each goal is worth $" + dollarsPerGoalText + "\nYou have earned back $" + dollarsEarnedBackText);
		}
	
	private boolean[] areAllGoalsCompleted() {
		int count = (listView.getLastVisiblePosition() - listView.getFirstVisiblePosition() + 1);
		boolean[] goalCompletion = new boolean[count];
		for (int i = 0; i< count; i++) {
			if (mainAdapter.getItem(i).isCompleted()) {
				goalCompletion[i] = true;
			}
		}
		return goalCompletion;
	}
	
	private void archiveCompletedGoals() {
		for (int i=0; i < mainAdapter.getCount(); i++) {
			mainAdapter.getItem(i).setArchived(true);
			mainAdapter.getItem(i).saveInBackground();
		}
	}
	
	private void newGoal() {
		Intent i = new Intent(this, NewGoalActivity.class);
		startActivityForResult(i, 0);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			updateGoalList();
			updateDollarsEarned();
		}
	}

}
