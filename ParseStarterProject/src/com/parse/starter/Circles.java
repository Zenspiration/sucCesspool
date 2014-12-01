import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Circles")
	  public class Circles extends ParseObject {
	   
	 //pulling each of the items & setting them: Circles Title, First User, Goals, (Second User, Third User...).  
	      public Circles() {
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
