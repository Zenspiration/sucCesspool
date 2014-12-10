package com.parse.starter;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.ParseObject;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.Toast;
import android.app.Service;
import android.app.*;


public class MyService extends Service {

ParseUser currentUser = ParseUser.getCurrentUser();
MyCounter timer;
String circleName;
int cycleLength;
public static int millisUntilFinishedInt;
public static int hours;
public static int minutes;
public static int seconds;


@Override
public void onCreate() {
    // TODO Auto-generated method stub
    timer = new MyCounter(100000,1000);
    super.onCreate();
   
    
    ParseQuery<Circle> query = ParseQuery.getQuery("Circle");	    	
   	query.whereEqualTo("userId", currentUser.getObjectId());
   	//query.whereEqualTo("archive", false);
   	query.getFirstInBackground(new GetCallback<Circle>() 
   	{

       	public void done(Circle circle, ParseException e) 
       	{
       		if (e == null) 
       		{
       			circleName = circle.getString("name");
   	            cycleLength = circle.getInt("cycleLength");   
       		}
       	}
   	});
}

@Override
public int onStartCommand(Intent intent, int flags, int startId) {
    // TODO Auto-generated method stub
    timer.start();
    return super.onStartCommand(intent, flags, startId);
}
private class MyCounter extends CountDownTimer{

	int millisecondsInCycle=cycleLength*24*60*60*1000;
	long millisInFuture=(long)millisecondsInCycle;
	long countDownInterval=1000;
	
    public MyCounter(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }
    
    
    //new CountDownTimer aCounter = new CountDownTimer(millisecondsInCycle , 1000) {
   

    //@Override
    public void onFinish() {
        //Toast.makeText(getApplicationContext(), "death", Toast.LENGTH_LONG).show();
        stopSelf();
    }

    //@Override
    public void onTick(long millisUntilFinished) {
	    	millisUntilFinishedInt= (int) millisUntilFinished;
	    	hours= millisUntilFinishedInt/3600000;
	    	minutes= (millisUntilFinishedInt%3600000)/60000;
	    	seconds= ((millisUntilFinishedInt%3600000)%60000)/1000;
	        //timeRemaining.setText(hours+" hours "+minutes+" minutes "+seconds+" seconds");
        //Toast.makeText(getApplicationContext(), (hours+" hours "+minutes+" minutes "+seconds+" seconds"), Toast.LENGTH_SHORT).show();
}

/*@Override
public void onDestroy() {
    // TODO Auto-generated method stub
    timer.cancel();
    //stopSelf();
    super.onDestroy();

}*/

    }
@Override
public IBinder onBind(Intent intent) {
	// TODO Auto-generated method stub
	return null;
}
}
