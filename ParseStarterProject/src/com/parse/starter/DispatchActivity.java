package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Activity which starts an intent for either the logged in (MainActivity) or logged out
 * (SignUpOrLoginActivity) activity.
 */
public class DispatchActivity extends Activity {

  public DispatchActivity() {
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Check if there is current user info
    ParseUser user= ParseUser.getCurrentUser();
    String userId=user.getObjectId();
    if (user != null) {
      // Start an intent for the logged in activity
    	// Query all the circles that exist
    	ParseQuery<ParseObject> query = ParseQuery.getQuery("Circle");
    	query.whereEqualTo("userId", userId);
    	//if their user Id exist under a circle, send them to MainActivity.java.
    	if (query!=null){
    		startActivity(new Intent(this, MainActivity.class));
    	} 
    	//if their user Id does NOT exist under a circle (ie. they don't have a circle) send them to CreateCircleActivity.java
    	else{
    		startActivity(new Intent(this, CreateCircleActivity.class));
    	}
    } else {
      // Start an intent for the logged out activity
      startActivity(new Intent(this, WelcomeActivity.class));
    }
  }

}