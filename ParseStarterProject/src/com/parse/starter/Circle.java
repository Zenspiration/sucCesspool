package com.parse.starter;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Circle")
	  public class Circle extends ParseObject {
	   
	 //pulling each of the items & setting them: Circles Title, First User, Goals, (Second User, Third User...).  
	      public Circle() {
	          // A default constructor is required.
	      }
	   
	      public boolean isArchived(){
	  		return getBoolean("archive");
	  	  }
	  	
	      public void setArchived(boolean archive) {
	  		put ("archive", archive);
	  	  }
	      
	      public String getCircleName() {
	          return getString("circleName");
	      }
	   
	      public void setCircleName(String name) {
	          put("name", name);
	      }
	   
	      public int getCycleLength() {
	    	  return getInt("cycleLength");
	      }
	      public void setCycleLength(int days) {
	    	  put("cycleLength", days);
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
	      
	      public String getCharity() {
	    	  return getString("charity");
	      }
	      public void setCharity(String charity) {
	    	  put("charity", charity);
	      }
	     	      
	      public int getFirstUsersPoints() {
	          return getInt("points");
	      }
	   
	      public void setFirstUsersPoints(int points) {
	          put("points", points);
	      } 
	      
	      public double getDollarsCommitted(){
	    	  return getDouble("dollars");
	      }
	      public void setDollarsCommitted(double money){
	    	  put("dollars", money);
	      }
	      public String getUserId(){
	    	  return getString("userId");
	      }
	      public void setUserId(String userId){
	    	  put("userId", userId);
	      }

	}
