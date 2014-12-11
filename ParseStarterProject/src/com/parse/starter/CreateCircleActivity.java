//CreateCircleActivity allows the user to create a new pool by inputting:
	//name of the pool, number of days the cycle runs for, initial amount of money committed, and charity to send remaining money to if goals remain uncompleted
	//after the circle is created, this page links to the "View Your Pool" page (CircleDisplayActivity.java)

package com.parse.starter;

//import android.support.v7.app.ActionBarActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import com.parse.ParseObject;
import com.parse.ParseUser;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;

//trying to get alarm to run in background
/*import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;*/

public class CreateCircleActivity extends Activity {

	//displays the pool created by the current logged in user
	ParseUser currentUser = ParseUser.getCurrentUser();
	//creates a new instance of the Circle class
	Circle circle = new Circle();

//trying to get alarm to run in background
/*final static private long ONE_SECOND = 1000;
final static private long TWENTY_SECONDS = ONE_SECOND * 20;
PendingIntent pi;
BroadcastReceiver br;
AlarmManager am;*/

public static int launchYear;
public static int launchMonth;
public static int launchDay;
public static long launchTime;


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_circle);  //sets layout of the page to activity_create_circle.xml
        
        
        //creates a "Create Pool" button that saves user input to Circle class and send user to "View Your Pool" page
        Button buttonCreatePool = (Button)findViewById(R.id.buttonCreatePool);
        buttonCreatePool.setOnClickListener(new View.OnClickListener()
        
        {
        	public void onClick(View v)
        	{
    			//learned how to connect front-end and back-end of user input text boxes from Android Development Tutorial
        		EditText inputCircleName = (EditText)findViewById(R.id.inputCircleName);
                EditText inputCycleLength = (EditText)findViewById(R.id.inputCycleLength);
                EditText inputMoneyCommitted = (EditText)findViewById(R.id.inputMoneyCommitted);
                EditText inputCharity = (EditText)findViewById(R.id.inputCharity);

                circle.setUserId(currentUser.getObjectId());
                circle.setArchived(false);

        		boolean validationError = false;
        		StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro2));
        		
        		//gets launch time
        		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		        launchYear=Integer.parseInt(timeStamp.substring(0,4));
		        launchMonth=Integer.parseInt(timeStamp.substring(4,6));
		        launchDay=Integer.parseInt(timeStamp.substring(6,8));
		        launchTime= Calendar.getInstance().getTimeInMillis();
		        
		        circle.put("launchTime", launchTime);
		        circle.put("launchYear", launchYear);
		        circle.put("launchMonth",launchMonth);
		        circle.put("launchDay", launchDay);

                //makes sure inputCycleLength is an integer
                try{
                	 circle.setCycleLength(Integer.parseInt(inputCycleLength.getText().toString()));
                } catch (Exception e){
                	validationError=true;
                	validationErrorMessage.append(getString(R.string.cycle_length_error));
                }
                
                //makes sure inputMoneyCommitted is a double
                try{
                	 circle.setDollarsCommitted(Double.parseDouble(inputMoneyCommitted.getText().toString()));
                } catch (Exception e){
                	validationError=true;
                	validationErrorMessage.append(getString(R.string.money_committed_error));
                }
                
                //makes sure inputCircleName is a real string
                String name = new String(inputCircleName.getText().toString());
                if (name.length() == 0)
                {
                	validationError=true;
                	validationErrorMessage.append(getString(R.string.circle_name_error));
                }
                else if (name.length() != 0 && name.charAt(0) == (' '))
                {
                	char firstCharacter = name.charAt(0);
                	while (firstCharacter == ' ' && name.length() > 0)
                	{
                		name = name.substring(1,name.length());
                	}
                	if (name.length() == 0)
                    {
                    	validationError=true;
                    	validationErrorMessage.append(getString(R.string.circle_name_error));
                    }
                	circle.setCircleName(inputCircleName.getText().toString());
                }
                circle.setCircleName(inputCircleName.getText().toString());
                
                //makes sure inputCharity is a real string
                String charity = new String(inputCharity.getText().toString());
                if (charity.length() == 0)
                {
                	validationError=true;
                	validationErrorMessage.append(getString(R.string.charity_input_error));
                }
                else if (charity.length() != 0 && charity.charAt(0) == (' '))
                {
                	char firstCharacter = charity.charAt(0);
                	while (firstCharacter == ' ' && charity.length() > 0)
                	{
                		charity = charity.substring(1,charity.length());
                	}
                	if (charity.length() == 0)
                	{
                		validationError=true;
                    	validationErrorMessage.append(getString(R.string.charity_input_error));
                	}
                	circle.setCharity(inputCharity.getText().toString());
                }
                circle.setCharity(inputCharity.getText().toString());
                
                //displays validation error
                if (validationError) {
                    Toast.makeText(CreateCircleActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                        .show();
                    return;
                  }
                
                //learned how to save user input to Circle class from Parse Android tutorial
                circle.saveInBackground();
                
        		//when "Create Pool" button is clicked, sets up an intent that takes the user to CircleDisplayActivity
        	    Intent intent = new Intent(CreateCircleActivity.this, CircleDisplayActivity.class);
        		startActivity(intent);
        		
        		//startService(serviceIntent);
        	}
        });    
     }  
   }

//deleted this functionality:
	//when user clicks "Click me to calculate money per day", app takes user input for inputCycle Length and inputMoneyCommitted
		//to calculate what each day is worth and to print it out to the screen
//Button buttonCalculate = (Button)findViewById(R.id.buttonCalculate);      
//buttonCalculate.setOnClickListener(new View.OnClickListener()

/*        {
        	public void onClick(View v)
        	{
                
                double moneyPerDay = Math.round(moneyCommitted / cycleLength);
            	//the following line from Stack Overflow rounds the money value to 2 decimal points
                String moneyPerDayRounded = String.format("%.2f", moneyPerDay);
                //put the doubles inside of a try catch in case users input a non-integer. put the money per day and rounded in there
                //because otherwise there were errors saying that cycle length and money committed didn't exist

                TextView display = (TextView)findViewById(R.id.displayMoneyPerDay);
                display.setText("Each day is worth $" + moneyPerDayRounded + ".");
        	}
        });
    }
*/
