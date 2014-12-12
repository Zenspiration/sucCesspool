//This is a class called "Circle" 
//This class stores all user input from CreateCircleActivity.java, and keeps it in this class 
//The purpose is so other pages can call this information from Circle.java in the future
//Ex: CircleDisplayActivity.java calls "cycleLength" from this file 

package com.parse.starter;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

//Initiate the class, "Circle"
@ParseClassName("Circle")
	  public class Circle extends ParseObject {
	   
	 //pulling each of the items & setting them: Circle title, length, charity associated, etc.   
	      public Circle() {
	      }
	   
	      public boolean isArchived(){
	  		return getBoolean("archive");
	  	  }
	  	
	      public void setArchived(boolean archive) {
	  		put ("archive", archive);
	  	  }
	      
	      public String getCircleName() {
	          return getString("name");
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
	      
	      public String getCharity() {
	    	  return getString("charity");
	      }
	      public void setCharity(String charity) {
	    	  put("charity", charity);
	      }

	      public double getDollarsCommitted(){
	    	  return getDouble("dollars");
	      }
	      public void setDollarsCommitted(double dollars){
	    	  put("dollars", dollars);
	      }
	      public double getDollarsEarned(){
	    	  return getDouble("dollarsEarned");
	      }
	      public void setDollarsEarned(double dollars){
	    	  put("dollarsEarned", dollars);
	      }
	      public String getUserId(){
	    	  return getString("userId");
	      }
	      public void setUserId(String userId){
	    	  put("userId", userId);
	      }

	}
