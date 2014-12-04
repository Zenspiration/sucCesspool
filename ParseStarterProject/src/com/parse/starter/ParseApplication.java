package com.parse.starter;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ParseApplication extends Application {

	  private static ConfigHelper configHelper;

	  public ParseApplication() {
	  }
	 
  @Override
  public void onCreate() {
    super.onCreate();

    // Add your initialization code here
    Parse.initialize(this, "0v803PGEiJuMloiMs2UAU1SGC56oEqhhV3VrM7Zg", "WCHokdalpkPU9qPmZyoSXTC71KaRSTjHJtmyiMqq");


    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
      
    // If you would like all objects to be private by default, remove this line.
    defaultACL.setPublicReadAccess(true);
    
    ParseACL.setDefaultACL(defaultACL, true);
    ParseObject.registerSubclass(Goal.class);
    ParseObject.registerSubclass(Circle.class);
    configHelper = new ConfigHelper();
    configHelper.fetchConfigIfNeeded();
  }
  
  public static ConfigHelper getConfigHelper() {
    return configHelper;
  }
}
