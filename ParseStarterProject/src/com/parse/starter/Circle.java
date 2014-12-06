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
	   
	      public String getCircleName() {
	          return getString("circleName");
	      }
	   
	      public void setCircleName(String name) {
	          put("name", name);
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
	      
	      public double moneyPerDay(){
	    	  return getInt("money per day");
	      }
	      
	      public void setMoneyPerDay(double money){
	    	  put("money per day", money);
	      }
	      
	      public int getCycleLength(){
	    	  return getInt("cycle length");
	      }
	      
	      public void setCycleLength(int cycleLength){
	    	  put("cycle length", cycleLength);
	      }
	      
	      public String getCharity(){
	    	  return getString("charity");
	      }
	      
	      public void setCharity(String charity){
	    	  put("charity", charity);
	      }

	}
