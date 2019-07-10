package com.example.a3130project.Fragments;

import androidx.fragment.app.Fragment;

import android.app.NotificationManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.a3130project.Activities.LoginActivity;
import com.example.a3130project.Helpers.NotificationSender;
import com.example.a3130project.R;


public class HomeFragment extends Fragment
{
	private NotificationManager mNotifyManager;

	private static final int    NOTIFICATION_ID    = 0;
	private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
	private              Button welcome;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		// Defines the xml file for the fragment
		return inflater.inflate(R.layout.activity_main, parent, false);
	}


/*	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getActivity().setContentView(R.layout.activity_main);
		//ToolBarCreator.createToolbar(this,true,false);
		//ToolBarCreator.createBottomNav(getActivity());
		welcome = getActivity().findViewById(R.id.buttonWelcome);
		Button alarmTest = getActivity().findViewById(R.id.buttonTestAlarm);
		alarmTest.setOnClickListener(new AlarmTester());
		welcome.setOnClickListener(new onClicker());
		NotificationSender.createNotificationChannel(getActivity());
	}
*/

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
					= NotificationSender.scheduleNotification(getActivity().getApplicationContext(),
					                                          System.currentTimeMillis());
			if ( !notificationSent )
			{
				Toast.makeText(getActivity().getApplicationContext(),
				               "Notifications disabled",
				               Toast.LENGTH_SHORT)
				     .show();
			}
		}
	}


	public void launchLogin()
	{
		Intent intent = new Intent(getActivity(), LoginActivity.class);
		startActivity(intent);
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
