//This page makes the first page that the user sees, where they can sign up or login
//We set up buttons & intents that send the user to SignUpActivity.java and LoginAcitivity.java 

package com.parse.starter;

import com.parse.ParseUser;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WelcomeActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);
    
    	// Click button to log in, intent sends user to LoginActivity.class 
        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new OnClickListener() {
          public void onClick(View v) {
            // Starts an intent of the log in activity
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
          }
        });

        // Click button to sign up, intent sends user to SignUpActivity.class
        Button signupButton = (Button) findViewById(R.id.signup_button);
        signupButton.setOnClickListener(new OnClickListener() {
          public void onClick(View v) {
            // Starts an intent for the sign up activity
            startActivity(new Intent(WelcomeActivity.this, SignUpActivity.class));
          }
        });
    }

    
  }

