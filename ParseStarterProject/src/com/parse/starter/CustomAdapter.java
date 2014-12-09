package com.parse.starter;

import com.parse.ParseFile;
import com.parse.ParseImageView;
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
				ParseQuery query = new ParseQuery("title");
				query.whereEqualTo("userId", currentUser.getObjectId());
				query.whereEqualTo("archive", false);
				return query;
			}
		});
	}

	// Customize the layout by overriding getItemView
<<<<<<< HEAD
//	@Override
//	public View getItemView(Goal goal, View v, ViewGroup parent) {
//		if (v == null) {
//			v = View.inflate(getContext(), R.layout.list_item, null);
//		}
//
//		// Add the title view
//		TextView titleTextView = (TextView) v.findViewById(R.id.text1);
//		titleTextView.setText(goal.getString("title"));
//
//		// Add a reminder of how long this item has been outstanding
//		TextView timestampView = (TextView) v.findViewById(R.id.timestamp);
//		timestampView.setText(goal.getCreatedAt().toString());
//		return v;
//	}
=======
	//@Override
	/*public View getItemView(Goal goal, View v, ViewGroup parent) {
		if (v == null) {
			v = View.inflate(getContext(), R.layout.list_item, null);
		}

		// Add the title view
		TextView titleTextView = (TextView) v.findViewById(R.id.text1);
		titleTextView.setText(goal.getString("title"));

		// Add a reminder of how long this item has been outstanding
		TextView timestampView = (TextView) v.findViewById(R.id.timestamp);
		timestampView.setText(goal.getCreatedAt().toString());
		return v;
	}*/
>>>>>>> FETCH_HEAD

}
