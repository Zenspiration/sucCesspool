package com.parse.starter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/*
This fragment manages the data entry for a
 new goal. Code was mostly adapted directly from Parse's MealSpotting Tutorial 
*/
public class NewGoalFragment extends Fragment {

	private Button saveButton;
	private Button cancelButton;
	private EditText goalName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle SavedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_new_goal, parent, false);

		goalName = (EditText) v.findViewById(R.id.goal_name);

		saveButton = (Button) v.findViewById(R.id.save_button);
		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Goal goal = ((NewGoalActivity) getActivity()).getCurrentGoal();

				// When the user clicks "Save," goal is uploaded to Parse with the following info:
				goal.setTitle(goalName.getText().toString());
				goal.setCompleted(false);
				goal.setArchived(false);
				//Default background color is set to black (Constant for which is 17170446). When user clicks
				//goal to complete it, the background color will be changed to green [see GoalListActivity.java]
				goal.setBackgroundColor(17170446);

				// This code associates the goal with the current user
				goal.setAuthor(ParseUser.getCurrentUser().getObjectId());
				goal.saveInBackground(new SaveCallback() {
			
					@Override
					public void done(ParseException e) {
						if (e == null) {
							getActivity().setResult(Activity.RESULT_OK);
							getActivity().finish();
						} else {
							Toast.makeText(
									getActivity().getApplicationContext(),
									"Error saving: " + e.getMessage(),
									Toast.LENGTH_SHORT).show();
						}
					}

				});

			}
		});

		cancelButton = (Button) v.findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().setResult(Activity.RESULT_CANCELED);
				getActivity().finish();
			}
		});
		return v;

		
	}
}


