package com.parse.starter;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.text.*;
import android.view.*;
import android.widget.*;

/*
 * This code launches NewGoalFragment, the window the user uses to enter new goals. 
 * Code was mostly adapted directly from Parse's MealSpotting Tutorial
 */

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

