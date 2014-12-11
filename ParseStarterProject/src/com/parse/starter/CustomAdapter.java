package com.parse.starter;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;
import android.content.Context;

//Custom ParseQueryAdapter called by the ListView in GoalListActivity
//Code adapted mostly from ParseQueryAdapter tutorial 
public class CustomAdapter extends ParseQueryAdapter<Goal> {

static ParseUser currentUser = ParseUser.getCurrentUser();

	public CustomAdapter(Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<Goal>() {
			@SuppressWarnings("unchecked")
			public ParseQuery create() {
				ParseQuery query = new ParseQuery("Goal");
				query.whereEqualTo("userId", currentUser.getObjectId());
				query.whereEqualTo("archive", false);
				return query;
			}
		});
	}

//Overrides view of each row item in the ListView. We set this up primarily to 
//change the background color of each goal permanently on click 
	@Override
	public View getItemView(Goal goal, View v, ViewGroup parent) {
	super.getItemView(goal, v, parent);
		if (v == null) {
			v = View.inflate(getContext(), R.layout.goal_list_item, null);
		}
		//calls the goal's backgroundColor as saved in Parse. It's set to black upon
		//initial saving of the goal, and green after the user clicks it to register completion
		int color = goal.getInt("backgroundColor");
		// Add the title view
		TextView titleTextView = (TextView) v.findViewById(R.id.text1);
		titleTextView.setText(goal.getString("title"));
		titleTextView.setBackgroundResource(color);
		
		return v;
	}
}
