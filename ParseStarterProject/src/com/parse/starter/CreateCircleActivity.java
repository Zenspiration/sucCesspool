package com.parse.starter;

//import android.support.v7.app.ActionBarActivity;
<<<<<<< HEAD
import com.parse.ParseObject;
=======
>>>>>>> origin/master
import com.parse.ParseUser;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.app.ListActivity;

public class CreateCircleActivity extends ListActivity {

ParseUser currentUser = ParseUser.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_circle);
        
        //when user clicks "Click me to calculate money per day",
        	//app takes user input for inputCycle Length and inputMoneyCommitted
        	//to calculate what each day is worth and to print it out to the screen
        //Button buttonCalculate = (Button)findViewById(R.id.buttonCalculate);      
        //buttonCalculate.setOnClickListener(new View.OnClickListener()

		EditText inputCircleName = (EditText)findViewById(R.id.inputCircleName);
        EditText inputCycleLength = (EditText)findViewById(R.id.inputCycleLength);
        EditText inputMoneyCommitted = (EditText)findViewById(R.id.inputMoneyCommitted);
        EditText inputCharity = (EditText)findViewById(R.id.inputCharity);

        double cycleLength=0;
        double moneyCommitted=0;
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
        
        //makes sure inputCycleLength is an integer
        try{
        	cycleLength = Integer.parseInt(inputCycleLength.getText().toString());	
        } catch (Exception e){
        	validationError=true;
        	validationErrorMessage.append(getString(R.string.cycle_length_error));
        }
        
        //makes sure inputMoneyCommitted is an integer
        try{
        	moneyCommitted = Integer.parseInt(inputMoneyCommitted.getText().toString());
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
        
        Circle c1= new Circle();
        c1.setCircleName(inputCircleName.getText().toString());
        c1.setFirstUsersPoints((int)moneyCommitted);
        c1.setCycleLength((int)cycleLength);
        c1.setCharity(inputCharity.getText().toString());
        c1.saveInBackground();
        
        ParseObject dollars = new ParseObject("dollars");
        dollars.put("value", moneyCommitted);
        dollars.put("user", currentUser);
        dollars.saveInBackground();
       
        
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
                
<<<<<<< HEAD
=======
                Circle c1= new Circle();
                c1.setCircleName(inputCircleName.getText().toString());
                c1.setFirstUsersPoints((int)moneyCommitted);
                c1.setMoneyPerDay(moneyPerDay);
                c1.setFirstUser(ParseUser.getCurrentUser());
                c1.saveInBackground();
>>>>>>> origin/master
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
}
