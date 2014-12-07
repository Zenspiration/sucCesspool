package com.parse.starter;

import com.parse.ParseUser;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Activity which displays a registration screen to the user.
 */
public class WelcomeActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);
    
    ParseUser currentUser = ParseUser.getCurrentUser();
    if (currentUser != null) {
    	Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
    } else {
    	// Log in button click handler
        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new OnClickListener() {
          public void onClick(View v) {
            // Starts an intent of the log in activity
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
          }
        });

        // Sign up button click handler
        Button signupButton = (Button) findViewById(R.id.signup_button);
        signupButton.setOnClickListener(new OnClickListener() {
          public void onClick(View v) {
            // Starts an intent for the sign up activity
            startActivity(new Intent(WelcomeActivity.this, SignUpActivity.class));
          }
        });
    }

    
  }
}
