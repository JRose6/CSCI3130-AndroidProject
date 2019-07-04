package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity
{
	private NotificationManager mNotifyManager;

	private static final int    NOTIFICATION_ID    = 0;
	private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
	private              Button welcome;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ToolBarCreator.createToolbar(this);
		ToolBarCreator.createBottomNav(this);
		welcome = findViewById(R.id.buttonWelcome);
		Button alarmTest = findViewById(R.id.buttonTestAlarm);
		alarmTest.setOnClickListener(new AlarmTester());
		welcome.setOnClickListener(new onClicker());
		NotificationSender.createNotificationChannel(this);

		if ( FirebaseAuth.getInstance().getCurrentUser() == null )
		{
			launchLogin();
		}
	}


	public class onClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			launchLogin();
		}
	}


	public class AlarmTester implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			boolean notificationSent
					= NotificationSender.scheduleNotification(getApplicationContext(),
					                                          System.currentTimeMillis());
			if ( !notificationSent )
			{
				Toast.makeText(getApplicationContext(),
				               "Notifications disabled",
				               Toast.LENGTH_SHORT)
				     .show();
			}
		}
	}


	public void launchLogin()
	{
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this, menu);
	}


	@Override
	protected void onResume()
	{
		super.onResume();
		if ( FirebaseAuth.getInstance().getCurrentUser() == null )
		{
			launchLogin();
		}
	}


	/**
	 * This is a simple logging function to dump a tag and message to the log surrounded by empty
	 * lines (so it's easier to find)
	 *
	 * @param tag
	 * @param message
	 */
	static void logg(String tag, String message)
	{
		Log.println(5, "", "");
		Log.println(5, "", "");
		Log.println(5, tag, message);
		Log.println(5, "", "");
		Log.println(5, "", "");
	}
} // end class
