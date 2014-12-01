import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.parse.ParseClassName;
//import android.support.v7.app.ActionBarActivity;

@ParseClassName("Pool")
	  public class Pool extends ParseObject {
	   
	 //pulling each of the items & setting them: Pool Title, First User, Goals, (Second User, Third User...).  
	      public Pool() {
	          // A default constructor is required.
	      }
	   
	      public String getPoolTitle() {
	          return getString("poolTitle");
	      }
	   
	      public void setPoolTitle(String title) {
	          put("title", title);
	      }
	   
	      public ParseUser getFirstUser() {
	          return getParseUser("firstUser");
	      }
	   
	      public void setFirstUser(ParseUser user) {
	          put("firstUser", user);
	      }
	   
	      public String getGoals() {
	          return getString("goals");
	      }
	   
	      public void setGoals(String goals) {
	          put("goals", goals);
	      } 
	      
	      public int getFirstUsersPoints() {
	          return getInt("points");
	      }
	   
	      public void setFirstUsersPoints(int points) {
	          put("points", points);
	      } 

	}

	public class PoolOverview extends ListActivity {
	 
		private ParseQueryAdapter<Pool> mainAdapter;  
		
		@Override
		//List of users & goals 
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			getListView().setClickable(false);
	
			mainAdapter = new ParseQueryAdapter<Pool>(this, Pool.class);
			mainAdapter.setTextKey("title");
			mainAdapter.setTextKey("users");
			mainAdapter.setTextKey("goals");
			mainAdapter.setTextKey("points");
	
			// Default view is all users & goals 
			setListAdapter(mainAdapter);
		}
	
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.activity_meal_list, menu);
			return true;
		}
	
		/*
		 * Posting meals and refreshing the list will be controlled from the Action
		 * Bar.
		 */
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
	
			case R.id.action_refresh: {
				updatePoolList();
				break;
			}
	
			}
			return super.onOptionsItemSelected(item);
		}
	
		private void updatePoolList() {
			mainAdapter.loadObjects();
			setListAdapter(mainAdapter);
		}
	
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			if (resultCode == Activity.RESULT_OK) {
				// If a new post has been added, update
				// the list of posts
				updatePoolList();
			}
		}
	
	}
