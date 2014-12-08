package com.parse.starter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

public class CustomAdapter extends ParseQueryAdapter<Goal> {

static ParseUser currentUser = ParseUser.getCurrentUser();

	public CustomAdapter(Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<Goal>() {
			@SuppressWarnings("unchecked")
			public ParseQuery create() {
				ParseQuery query = new ParseQuery("title");
				query.whereEqualTo("userId", currentUser.getObjectId());
				query.whereEqualTo("archive", false);
				return query;
			}
		});
	}

	// Customize the layout by overriding getItemView
//	@Override
//	public View getItemView(ParseObject object, View v, ViewGroup parent) {
//		if (v == null) {
//			v = View.inflate(getContext(), R.layout.activity_goal_list, null);
//		}
//
//		// Add the title view
//		TextView titleTextView = (TextView) v.findViewById(R.id.text1);
//		titleTextView.setText(object.getString("title"));
//
//		// Add a reminder of how long this item has been outstanding
//		TextView timestampView = (TextView) v.findViewById(R.id.timestamp);
//		timestampView.setText(object.getCreatedAt().toString());
//		return v;
//	}

}