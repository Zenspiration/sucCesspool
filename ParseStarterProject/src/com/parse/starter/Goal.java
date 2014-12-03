package com.parse.starter;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Goal")
public class Goal extends ParseObject{
	public Goal() {
        // A default constructor is required.
    }
 
	public boolean isCompleted(){
		return getBoolean("completed");
	}
	
	public void setCompleted(boolean complete) {
		put ("completed", complete);
	}
	
    public String getTitle() {
        return getString("title");
    }
 
    public void setTitle(String title) {
        put("title", title);
    }
    
    public void saveGoal(){
    	//ParseObject= goal
    }

	public void setAuthor(ParseUser currentUser) {
		// TODO Auto-generated method stub
		
	}

}

