package com.parse.starter;

//import android.support.v7.app.ActionBarActivity;
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

public class CreateCircleActivity extends Activity {

ParseUser currentUser = ParseUser.getCurrentUser();
Circle circle = new Circle();

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_circle);        
        
        
        Button buttonCreatePool = (Button)findViewById(R.id.buttonCreatePool);
        buttonCreatePool.setOnClickListener(new View.OnClickListener() 
        {
        	public void onClick(View v)
        	{
        		EditText inputCircleName = (EditText)findViewById(R.id.inputCircleName);
                EditText inputCycleLength = (EditText)findViewById(R.id.inputCycleLength);
                EditText inputMoneyCommitted = (EditText)findViewById(R.id.inputMoneyCommitted);
                EditText inputCharity = (EditText)findViewById(R.id.inputCharity);

        		//int cycleLength=0;
        		//double moneyCommitted=0;
        		/*boolean validationError = false;
        		//StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));

        		/*
                //makes sure inputCycleLength is an integer
                try{
                	cycleLength = Integer.parseInt(inputCycleLength.getText().toString());	
                } catch (Exception e){
                	validationError=true;
                	validationErrorMessage.append(getString(R.string.cycle_length_error));
                }
                
                //makes sure inputMoneyCommitted is an integer
                try{
                	moneyCommitted = Double.parseDouble(inputMoneyCommitted.getText().toString());
                } catch (Exception e){
                	validationError=true;
                	validationErrorMessage.append(getString(R.string.money_committed_error));
                }
                
                //displays validation error
                if (validationError) {
                    Toast.makeText(CreateCircleActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                        .show();
                    return;
                  }
                */
                
                circle.setCircleName(inputCircleName.getText().toString());
                circle.setDollarsCommitted(Double.parseDouble(inputMoneyCommitted.getText().toString()));
                circle.setCycleLength(Integer.parseInt(inputCycleLength.getText().toString()));
                circle.setCharity(inputCharity.getText().toString());
                //c1.setFirstUser(currentUser);
                circle.setUserId(currentUser.getObjectId());
                circle.saveInBackground();
                circle.setArchived(false);
        		
        	    Intent intent = new Intent(CreateCircleActivity.this, CircleDisplayActivity.class);
        		startActivity(intent);
        	}
        });    
     }
    
   }








//when user clicks "Click me to calculate money per day",
//app takes user input for inputCycle Length and inputMoneyCommitted
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
                

                Circle c1= new Circle();
                c1.setCircleName(inputCircleName.getText().toString());
                c1.setFirstUsersPoints((int)moneyCommitted);
                c1.setMoneyPerDay(moneyPerDay);
                c1.setFirstUser(ParseUser.getCurrentUser());
                c1.saveInBackground();

        	}
        });
    }
*/

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    } */

