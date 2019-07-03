package com.example.a3130project;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


public class AlarmBroadCastReceiver extends BroadcastReceiver
{
	String TAG = "AlarmReceiver";


	@Override
	public void onReceive(Context context, Intent intent)
	{
		Log.d(TAG, "::Received");
		SharedPreferences sharedPref
				= context.getSharedPreferences(context.getString(R.string.preference_file),
				                               Context.MODE_PRIVATE);
		if ( intent != null )
		{
			if ( sharedPref.getBoolean(context.getString(R.string.saved_alarms_allowed), false) )
			{
				NotificationSender.sendNotification(context);
				NotificationSender.scheduleNotification(context,
				                                        System.currentTimeMillis() + ( 60 * 1000 ));
			}
		}
	}
}
