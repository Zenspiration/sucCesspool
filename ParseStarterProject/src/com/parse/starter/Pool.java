package com.parse.starter;

import com.parse.ParseObject;
import com.parse.ParseUser;

//@ParseClassName("Goal")
public class Pool extends ParseObject{
	public Pool() {
        // A default constructor is required.
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