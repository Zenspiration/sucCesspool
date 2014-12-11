//This page is where a brand new user makes an account. 
//We store all of the user data in a Parse "user" class, so we can access the information later. 
//We use booleans & if statements to make sure there are no errors in signing up. 

package com.parse.starter;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


//this activity shows the user a login screen 
public class SignUpActivity extends Activity {

//these are for the front end 
  private EditText usernameEditText;
  private EditText passwordEditText;
  private EditText passwordAgainEditText;
  private EditText firstNameEditText;
  private EditText lastNameEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_signup);

    // Sets up the signup form with username, password, password repeat, first name & last name 
    usernameEditText = (EditText) findViewById(R.id.username_edit_text);
    passwordEditText = (EditText) findViewById(R.id.password_edit_text);
    passwordAgainEditText = (EditText) findViewById(R.id.password_again_edit_text);
    firstNameEditText= (EditText) findViewById(R.id.firstName_edit_text);
    lastNameEditText= (EditText) findViewById(R.id.lastName_edit_text);
    passwordAgainEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == R.id.edittext_action_signup ||
            actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
          signup();
          return true;
        }
        return false;
      }
    });

    // Sets up the "signup" button 
    Button mActionButton = (Button) findViewById(R.id.action_button);
    mActionButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        signup();
      }
    });
  }

  private void signup() {
    String username = usernameEditText.getText().toString().trim();
    String password = passwordEditText.getText().toString().trim();
    String passwordAgain = passwordAgainEditText.getText().toString().trim();
    String firstName= firstNameEditText.getText().toString().trim();
    String lastName= lastNameEditText.getText().toString().trim();

    // Ensures that the user entered something for each field & gives a pop-up response if you left a field blank 
    // Used booleans & if statements to make "validationError = true" when there is any sort of error
    boolean validationError = false;
    StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
    if (username.length() == 0) {
      validationError = true;
      validationErrorMessage.append(getString(R.string.error_blank_username));
    }
    if (password.length() == 0) {
      if (validationError) {
        validationErrorMessage.append(getString(R.string.error_join));
      }
      validationError = true;
      validationErrorMessage.append(getString(R.string.error_blank_password));
    }
    if (!password.equals(passwordAgain)) {
      if (validationError) {
        validationErrorMessage.append(getString(R.string.error_join));
      }
      validationError = true;
      validationErrorMessage.append(getString(R.string.error_mismatched_passwords));
    }
    validationErrorMessage.append(getString(R.string.error_end));

    // If validationError = true (meaning there was some mistake), pop up with a message 
    if (validationError) {
      Toast.makeText(SignUpActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
          .show();
      return;
    }

    final ProgressDialog dialog = new ProgressDialog(SignUpActivity.this);
    dialog.setMessage(getString(R.string.progress_signup));
    dialog.show();

    // Set up a new Parse "user" class that stores all of the signup info 
    ParseUser user = new ParseUser();
    user.setUsername(username);
    user.setPassword(password);
    user.put("firstName", firstName);
    user.put("lastName", lastName);
    

    // Call the Parse signup method
    user.signUpInBackground(new SignUpCallback() {
      @Override
      public void done(ParseException e) {
        dialog.dismiss();
        if (e != null) {
          // Show the error message
          Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        } else {
          // Intent to link the signup page to the login page so the user can get started :) 
          Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
        }
      }
    });
  }
}

