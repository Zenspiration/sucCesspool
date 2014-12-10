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
	
	@Override
	public View getItemView(Goal goal, View v, ViewGroup parent) {
	super.getItemView(goal, v, parent);
		if (v == null) {
			v = View.inflate(getContext(), R.layout.goal_list_item, null);
		}
		int color = goal.getInt("backgroundColor");
		// Add the title view
		TextView titleTextView = (TextView) v.findViewById(R.id.text1);
		titleTextView.setText(goal.getString("title"));
		titleTextView.setBackgroundResource(color);
		
		return v;
	}
}
