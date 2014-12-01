package com.parse.starter;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class NewGoalActivity extends Activity {
	  private Goal goal;
	  private EditText goalEditText;
	  private TextView characterCountTextView;
	  private Button goalButton;
	  private int maxCharacterCount = ParseApplication.getConfigHelper().getGoalMaxCharacterCount();

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        goal = new Goal();
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        super.onCreate(savedInstanceState);
	 

	        setContentView(R.layout.activity_new_goal);
	        
	    goalEditText = (EditText) findViewById(R.id.goal_edittext);
		  goalEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged (Editable s) {
				updateGoalButtonState();
				updateCharacterCountTextViewText();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
		  });
		  
			goalButton = (Button) findViewById(R.id.goal_button);
			goalButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					postGoal();
				}
			});
}
	 
	    public Goal getCurrentGoal() {
	        return goal;
	    }
	    
	    private void postGoal () {
	        String text = goalEditText.getText().toString().trim();

	        // Set up a progress dialog
	        final ProgressDialog dialog = new ProgressDialog(NewGoalActivity.this);
	        dialog.setMessage(getString(R.string.progress_post));
	        dialog.show();

	        // Create a post.
	        Goal goal = new Goal();
	     
	        goal.setTitle(text);
	        ParseACL acl = new ParseACL();

	        // Give public read access
	        acl.setPublicReadAccess(true);
	        goal.setACL(acl);

	        // Save the post
	        goal.saveInBackground(new SaveCallback() {
	          @Override
	          public void done(ParseException e) {
	            dialog.dismiss();
	            finish();
	          }
	        });
	      }
 
	    
	    private String getGoalEditTextText () {
	      return goalEditText.getText().toString().trim();
	    }
	    
	    private void updateGoalButtonState () {
	        int length = getGoalEditTextText().length();
	        boolean enabled = length > 0 && length < maxCharacterCount;
	        goalButton.setEnabled(enabled);
	      }

	      private void updateCharacterCountTextViewText () {
	        String characterCountString = String.format("%d/%d", goalEditText.length(), maxCharacterCount);
	        characterCountTextView.setText(characterCountString);
	      }
	  
}
