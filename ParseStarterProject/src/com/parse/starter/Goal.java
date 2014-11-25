package com.parse.starter;

import com.parse.ParseObject;

//@ParseClassName("Goal")
public class Goal extends ParseObject{
	public Goal() {
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

}
