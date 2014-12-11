package com.parse.starter;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/*
 * This file sets up the Goal class in Parse. Most of it is original code but the 
 * getTitle, setTitle and setAuthor methods were adapted from Parse's MealSpotting Tutorial. Idea
 * for having an isCompleted() boolean was from 
 * http://www.michaelevans.org/blog/2013/08/14/tutorial-building-an-android-to-do-list-app-using-parse/	
 */
@ParseClassName("Goal")
public class Goal extends ParseObject{
	public Goal() {
    }
 
/*The boolean "completed" is set to false when the user first creates a goal [see NewGoalFragment]
 When he/she clicks the goal to complete it, "completed" is set to true, which activates:
 1) change in background color of goal
 2) change in amount of dollars earned back 
[both in GoalListActvity]
 */
	public boolean isCompleted(){
		return getBoolean("completed");
	}
	
	public void setCompleted(boolean complete) {
		put ("completed", complete);
	}

/*
 * We set up an "archive" boolean so that once a cycle is complete we can store the user's goals
 * instead of deleting them. When the user starts another cycle we only query goals where 
 * archive == false
 */
	
	public boolean isArchived(){
		return getBoolean("archive");
	}
	public void setArchived(boolean archive) {
		put ("archive", archive);
	}
	
    public String getTitle() {
        return getString("title");
    }
 
    public void setTitle(String title) {
        put("title", title);
    }
    
    public void setBackgroundColor (int color) {
    	put("backgroundColor", color);
    }

/*
 * setAuthor associates each goal with the particular user who set it up so whenever we
 * call ParseQuery a user only sees his own goals
 */
    public void setAuthor(String author) {
        put("userId", author);
    }  

    public void saveGoal(){
    	//ParseObject= goal
    }

}

