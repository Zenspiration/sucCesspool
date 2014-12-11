package com.parse.starter;

import java.util.List;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/*
 * GoalListActivity is called after clicking the "View Goals" button in 
 * CircleDisplayActivity. It displays a list of all the user's goals, allows him/her
 * to add new ones, and is where he/she registers the completion of goals in each cycle 
 *  
 */

public class GoalListActivity extends ListActivity {
	ParseUser currentUser = ParseUser.getCurrentUser();
//Because we had specific requirements for our ParseQuery for goals, we had to 
//set up a CustomAdapter [see CustomAdapter.java]
	private CustomAdapter mainAdapter;
	private ListView listView;
//We need to use the amount of money the user committed to this cycle in a few methods,
	//so we set it up as a global variable 
	int dollarsCommitted;
	Circle currentCircle;

//Most of the code from here to "(end)" was taken from the MealSpotting tutorial, but we add in a setContentView
//line because we wanted to customize the layout of our goal list. (Added a header at the top and a 
//TextView telling the user how much money he/she has earned back
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_goal_list);
		mainAdapter = new CustomAdapter(this);
		mainAdapter.setTextKey("title");

		listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(mainAdapter);
		mainAdapter.loadObjects();
// (end)

/*The app is set up such that each user can only create one circle (there's no way to navigate to 
 *CreateCircleActivity if one has a circle active, but we decided to use getFirstInBackground
 *for our ParseQuery to be sure
 */
		 ParseQuery<Circle> query = ParseQuery.getQuery("Circle");
			query.whereEqualTo("userId", currentUser.getObjectId());
			query.whereEqualTo("archive", false);
			query.getFirstInBackground(new GetCallback<Circle>() {
			public void done(Circle circle, ParseException e) {
				// TODO Auto-generated method stub
				   if (e == null) {
			            dollarsCommitted = circle.getInt("dollars");
			            currentCircle = circle;
				   }
/*we added in this code to ensure that no error is thrown when the user first loads 
*GoalListActivity and hasn't set up any goals. When mainAdapter.getCount() == 0 our
*updateDollarsEarned method will attempt to divide by zero and cause a fatal exception
*/
			if(mainAdapter.getCount() == 0) {
			TextView dollarsearned = (TextView) findViewById(R.id.goal_moneyearned);
			dollarsearned.setText("You have $" + dollarsCommitted + " in your pool \nYou have not earned any money back yet");
			}
			else {
				updateDollarsEarned();
			}
			
		listView.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Goal goal = mainAdapter.getItem(position);
			goal.setCompleted(true);
//Background color of the goal in ListView is set to green upon completion. This change is permanent.
//The integer 17170452 calls "holo_green_light" from the default android.R.color class 
			goal.setBackgroundColor(17170452);
			goal.saveInBackground();
			updateGoalList();
			updateDollarsEarned();
	
/*Code for this section before "(end)" was original.  
 *A series of booleans that checks the completion status of all goals, 
 *and launches a pop up once all goals have been marked by the user as 
 *completed
 */

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

// (end)
	@Override
/*
 * Code for the OptionsMenu was taken from Parse's MealSpotting Tutorial 
 */
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

	
	private void updateGoalList() {
		mainAdapter.loadObjects();
		setListAdapter(mainAdapter);
	}
	
/*Basic code for pop-up window was adapted from
 * 1) Stack Overflow Post: http://stackoverflow.com/questions/23028480/android-how-to-create-popup-window
 *2) http://mrbool.com/how-to-implement-popup-window-in-android/28285
*/	
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
	 
					 String dollarsCommittedText = ("" + dollarsCommitted);
					 TextView message = (TextView) popupView.findViewById (R.id.goals_completed);
					 message.setText("Congratulations! You have completed your goals for this cycle and earned back $" + dollarsCommittedText);
					 			        		   				   
					 	
				           
				    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

						@Override
						public void onDismiss() {
						
							archiveCompletedGoals();// Sets "archive" == true for all goals so they can't be called from the database again
							updateGoalList();
							
							//Query the user's circle's to archive it as well
							ParseQuery<Circle> query = ParseQuery.getQuery("Circle");
					 			query.whereEqualTo("userId", currentUser.getObjectId());
					 			query.whereEqualTo("archive", false);
					 			query.getFirstInBackground(new GetCallback<Circle>() {
					 			public void done(Circle circle, ParseException e) {
					 				   if (e == null) {
					 			            circle.setArchived(true); 
					 			            circle.saveInBackground();
					 				   } 
					 			}
					 		});
					 		//sends user back to CreateCircleActivity once he dismisses the popup
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
	
/*
 * The rest of the code in this activity is original, except for onActivityResult 
 */

//Code that updates the TextView at the bottom that tells the user how much each of his/her goals is worth
//and how much money he/she has earned back 
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
		dollarsearned.setText("You have $" + dollarsCommitted + " in your pool \nEach goal is worth $" + dollarsPerGoalText + "\nYou have earned back $" + dollarsEarnedBackText);
		
		currentCircle.setDollarsEarned(dollarsEarnedBack);
		currentCircle.saveInBackground();	
	}

/*We had to set up a separate method to update the dollars earned after goal add because somehow no matter how 
*we ordered our methods in onActivityResult() [see below] mainAdapter.getCount() was always one less than it should have been
*after adding the new goal. Even when we nested updateDollarsEarned in updateGoalList and placed it after mainAdapter.loadObjects()
*we still faced the same problem. We figured this was the best way to retain functionality. 
**/
	private void updateDollarsEarnedAfterGoalAdd() {
		double dollarsPerGoal = (double)dollarsCommitted / (double)(mainAdapter.getCount() + 1);
		double dollarsEarnedBack = 0;
		for (int i = 0; i< mainAdapter.getCount(); i++) {
			if (mainAdapter.getItem(i).isCompleted()) {
				dollarsEarnedBack = dollarsEarnedBack + dollarsPerGoal;
			}
		}
		String dollarsEarnedBackText = String.format("%.2f", dollarsEarnedBack);
		String dollarsPerGoalText = String.format("%.2f", dollarsPerGoal);
		TextView dollarsearned = (TextView) findViewById(R.id.goal_moneyearned);
		dollarsearned.setText("You have $" + dollarsCommitted + " in your pool \nEach goal is worth $" + dollarsPerGoalText + "\nYou have earned back $" + dollarsEarnedBackText);
		
		currentCircle.setDollarsEarned(dollarsEarnedBack);
		currentCircle.saveInBackground();	
	}

	private boolean[] areAllGoalsCompleted() {
		boolean[] goalCompletion = new boolean[mainAdapter.getCount()];
		for (int i = 0; i< mainAdapter.getCount(); i++) {
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
			updateDollarsEarnedAfterGoalAdd();
		}
	}

}
