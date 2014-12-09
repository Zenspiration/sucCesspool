package com.parse.starter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/*
 * This fragment manages the data entry for a
 * new Meal object. It lets the user input a 
 * meal name, give it a rating, and take a 
 * photo. If there is already a photo associated
 * with this meal, it will be displayed in the 
 * preview at the bottom, which is a standalone
 * ParseImageView.
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

				// When the user clicks "Save," upload the meal to Parse
				// Add data to the meal object:
				goal.setTitle(goalName.getText().toString());
				goal.setCompleted(false);
				goal.setArchived(false);

				// Associate the meal with the current user
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


