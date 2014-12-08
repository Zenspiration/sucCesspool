package com.parse.starter;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
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

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	goal = new Goal();
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        super.onCreate(savedInstanceState);
	 

	        setContentView(R.layout.activity_new_goal);
			FragmentManager manager = getFragmentManager();
			Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

			if (fragment == null) {
				fragment = new NewGoalFragment();
				manager.beginTransaction().add(R.id.fragmentContainer, fragment)
						.commit();
			}
		}

		public Goal getCurrentGoal() {
			return goal;
		}

	}

