package com.parse.starter;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ParseApplication extends Application {

	  public ParseApplication() {
	  }
	 
  @Override
  public void onCreate() {
    super.onCreate();

    // Initialization code for our Parse account
    Parse.initialize(this, "0v803PGEiJuMloiMs2UAU1SGC56oEqhhV3VrM7Zg", "WCHokdalpkPU9qPmZyoSXTC71KaRSTjHJtmyiMqq");


    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    defaultACL.setPublicReadAccess(true);
    
    ParseACL.setDefaultACL(defaultACL, true);
    ParseObject.registerSubclass(Goal.class);
    ParseObject.registerSubclass(Circle.class);
  }
 
}
