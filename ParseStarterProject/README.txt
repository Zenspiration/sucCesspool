Overview of the SuccessPool App for Android
===================================================

SuccessPool is a productivity app for Android. Users can "dive in" to a goal pool by:
	1. Setting goals that they want to complete
	2. Committing a certain amount of money to achieving those goals
	3. Setting the number of days the pool will last (i.e. when they must complete goals by)
	4. Selecting a charity to donate to. 

As users complete their goals, they will incrementally earn back a % of the money they 
initially committed. When the cycle end, the user will lose a % of the money from
uncompleted goals. This money is will be donated to a charity of the user's choice. 

Leveraging psychological concepts such as loss aversion and mental accounting, SuccessPool
segregates gains and integrates losses to optimize productivity and goal commitment. 


*Important Notes, from us to the graders*
===================================================
	- We used Parse as a base for our project, especially for the database. A lot of effort was 
	  devoted to learning Parse-specific syntax! 
	- Our code is a mix of original code & adapted code from Parse tutorials. Our annotations
	  indicate which parts are original vs adapted. 
	- Circle = Pool. We used the word "circle" in our code, but "pool" on the front end because 
	  we switched terms halfway through the project (sorry!).
	- Below is an overview of each page's functionality & code. Please follow along as you review
	  each page of code :) 
	
*********************************************************************************************************

Sign Up or Log In
===================================================

Upon opening the Successpool app, users will see a WelcomeActivity where they can
log in or sign up. Main features of Signup & Sign in pages: 

	1. User input info is stored in the User class on Parse.
	2. A pop-up warning appears if the user left a field blank. 
	3. Pop-up warning if the username/pw doesn't match what was stored in the User class. 
	4. After a user logs in, the app will run the Main Activity, which either sends 
	the user to the "Create a New Pool" page, or to "View Your Pool", depending on 
	whether they currently have an active pool. 
	

Create a New Pool
===================================================
***NOTE: We used the words circle & pool interchangeably, because we changed terms halfway
through the project.

"Create a New Pool" is used when 1) a new user joins, or 2) an existing user's circle expired. 
Main features of this page:
   1. Choose a name for their goal pool, select how many days their pool will be active, 
   commit an integer amount of money to over the course of this cycle, and enter a charity 
   to donate the remaining money should they fail to complete all their goals. 
   2. The information that the user inputs will be saved to the User class on Parse. 
   
For all user input, we validate to make sure that:
   1. No empty strings in pool name & charity name fields 
   2. No string with only spaces in pool name & charity name fields  
   3. There is a valid integer for the cycle length and money committed fields. 

Two things happen once the user clicks the "Create Pool" button after inputting all the relevant 
information. The user will be directed to the "View Your Pool" page, and a countdown timer that
runs for the length of the cycle will be launched. 


View Your Pool
===================================================
If the user currently has a goal pool, they will be directed to CircleDisplayActivity. 
This page summarizes the facts about your pool, like the name & length of cycle.

*COUNTDOWN TIMER: This page also displays a live-updating "time remaining" feature, which shows 
how many hours, minutes, and seconds remain in the cycle as motivation. As users watch their 
seconds slip away in real time, they'll be motivated to complete their goals before their money 
slips away, too.

At this stage, our app uses "virtual money," expecting the user to commit real money to his/her 
pool outside of the app in real life. The user is expected to donate however much money that 
is not earned back to a real charity. In a more advanced version of this app, the user's SuccessPool 
account would be connected to Venmo or a bank account, where money would be physically transferred.


View Your Goals
===================================================
The "View Your Goals" button at the bottom of the "View Your Pool" page directs you to 
GoalListActivity. Main features of this page: 
	1. Displays all goals the user has added (see below to add a goal)
	2. User can click on a goal when it is complete, and the background will turn green to 
	indicate that it's completed. 
	3. Shows how much money is in the pool vs. how much has been returned to you. (This is based
	on what percentage of the goals you've completed). 

How the $/goal works:
The amount in the pool starts at your total commitment, say $20. It decreases as the user 
completes their goals. The worth of each goal is the total $20 divided by the number of goals 
the user has listed. For example, if you have 5 goals, each is worth $4. If you update to 
6 goals, each one becomes worth $3.33. 


Add a New Goal
===================================================
Click "+" in the top action bar to add a new goal. A new page will load where the user can input
a description of the goal and "Dive In!" to an updated display of the "View Your Goals" page. 

How the code works: Each new goal input is saved into the Goal class in Parse. When a goal is 
completed, the "completed" Boolean that is stored in this class changes from false to true. When 
the pool ends (either because all goals are completed, or because time is out), the "archived" 
Boolean stored in this class changes from false to true. Archived goals no longer appear in the
app, but are stored in Parse. 


Completing All Your Goals On Time 
===================================================
If the user completes all the goals in the pool before the cycle ends, then an automatic pop-up appears, 
congratulating the user. All goals within this pool, as well as the pool itself, will be archived in 
their respective classes in Parse. A "Dive into a New Pool" button in this pop-up will take the user 
back to the "Create a New Pool" page.


Uncompleted Goals at the End of the Cycle
===================================================
If the user is not so productive during his/her pool cycle, there may be goals left uncompleted by the
time the cycle ends. When the countdown timer reaches 0, the user will automatically be directed to
CircleExpired, an activity that tells the user that his/her cycle has ended. 

Features of CircleExpired: 
	1. Displays how much $ the user has earned back 
	2. Displays how much $ will be donated to charity (for failed goals) 
	3. Pool & goals will be archived
	4. Prompts user to "Dive into a new pool!" with a button 

Whether users succeed or fail, they can always dive into a new pool and try again, creating cycles 
upon cycles of commitment and productivity.    


Contacts
===================================================
To be informed about new code releases, bug fixes, and general news and information about the 
SuccessPool App for Android, please contact: 
	 kaity.hsieh@yale.edu
	 jarrell.ng@yale.edu 
	 devin.hilly@yale.edu
	 daniel.shao@yale.edu
	 lucia.chen@yale.edu
Thank you for using SuccessPool!
