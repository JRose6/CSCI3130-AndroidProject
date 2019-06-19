package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;


public class MainActivity extends AppCompatActivity
{
	private NotificationManager mNotifyManager;

	private static final int    NOTIFICATION_ID    = 0;
	private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
	private              Button welcome;


	public void sendNotification()
	{
		NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
		mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
	}


	private NotificationCompat.Builder getNotificationBuilder()
	{
		NotificationCompat.Builder notifyBuilder
				= new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID).setContentTitle("You've been notified!")
				.setContentText("This is your notification text.")
				.setSmallIcon(R.drawable.ic_alarm);
		return notifyBuilder;
	}


	public void createNotificationChannel()
	{
		mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
		{
			NotificationChannel notificationChannel
					= new NotificationChannel(PRIMARY_CHANNEL_ID, "Mascot Notification", NotificationManager.IMPORTANCE_HIGH);
			notificationChannel.enableLights(true);
			notificationChannel.enableVibration(true);
			notificationChannel.setDescription("Notification from Mascot");
			mNotifyManager.createNotificationChannel(notificationChannel);
		}
	}


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.showOverflowMenu();
		setSupportActionBar(toolbar);
		welcome = findViewById(R.id.buttonWelcome);
		Button alarmTest = findViewById(R.id.buttonTestAlarm);
		alarmTest.setOnClickListener(new AlarmTester());
		welcome.setOnClickListener(new onClicker());
		NotificationSender.createNotificationChannel(this);
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
					= NotificationSender.scheduleNotification(getApplicationContext(), System.currentTimeMillis());
			if (!notificationSent)
			{
				Toast.makeText(getApplicationContext(), "Notifications disabled", Toast.LENGTH_SHORT)
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
		getMenuInflater().inflate(R.menu.mainmenu, menu);
		//U can find item set icon and stuff...
		MenuItem item = menu.findItem(R.id.action_settings);
		item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
		{
			@Override
			public boolean onMenuItemClick(MenuItem item)
			{
				Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
				startActivity(intent);
				return false;
			}
		});
		MenuItem item2 = menu.findItem(R.id.action_refill);
		item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
		{
			@Override
			public boolean onMenuItemClick(MenuItem item)
			{
				Intent intent = new Intent(getApplicationContext(), RefillActivity.class);
				startActivity(intent);
				return false;
			}
		});
		return true;
	}


	/**
	 * This is a simple logging function to dump a tag and message to the log surrounded by
	 * empty lines (so it's easier to find)
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
