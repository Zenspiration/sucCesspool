package com.parse.starter;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

MyCounter timer;
@Override
public void onCreate() {
    // TODO Auto-generated method stub
    timer = new MyCounter(100000,1000);
    super.onCreate();
}

@Override
public int onStartCommand(Intent intent, int flags, int startId) {
    // TODO Auto-generated method stub
    timer.start();
    return super.onStartCommand(intent, flags, startId);
}
private class MyCounter extends CountDownTimer{

    public MyCounter(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }
    
    //int millisecondsInCycle=cycleLength*24*60*60*1000;
    //new CountDownTimer aCounter = new CountDownTimer(millisecondsInCycle , 1000) {

    @Override
    public void onFinish() {
        Toast.makeText(getApplicationContext(), "death", Toast.LENGTH_LONG).show();
        stopSelf();
    }

    @Override
    public void onTick(long millisUntilFinished) {
	    	int millisUntilFinishedInt= (int) millisUntilFinished;
	    	int hours= millisUntilFinishedInt/3600000;
	    	int minutes= (millisUntilFinishedInt%3600000)/60000;
	    	int seconds= ((millisUntilFinishedInt%3600000)%60000)/1000;
	        //timeRemaining.setText(hours+" hours "+minutes+" minutes "+seconds+" seconds");
        Toast.makeText(getApplicationContext(), (hours+" hours "+minutes+" minutes "+seconds+" seconds"), Toast.LENGTH_SHORT).show();
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
