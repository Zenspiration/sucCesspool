package com.parse.starter;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

//public class Application extends android.app.Application {
//
//  public Application() {
//  }
//
//  @Override
//  public void onCreate() {
//    super.onCreate();
//
//	// Initialize the Parse SDK.
//    Parse.initialize(this, "0v803PGEiJuMloiMs2UAU1SGC56oEqhhV3VrM7Zg", "WCHokdalpkPU9qPmZyoSXTC71KaRSTjHJtmyiMqq"); //Should there be quotes around the App ID and the Client Key?
//
//	// Specify an Activity to handle all pushes by default.
//	PushService.setDefaultPushCallback(this, MainActivity.class);
//	
//	ParsePush.subscribeInBackground("", new SaveCallback() {
//		  @Override
//		  public void done(ParseException e) {
//		    if (e == null) {
//		      Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
//		    } else {
//		      Log.e("com.parse.push", "failed to subscribe for push", e);
//		    }
//		  }
//		});
//  }
//}