package com.example.a3130project.Helpers;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.core.app.NotificationCompat;

import com.example.a3130project.R;
import com.example.a3130project.Receivers.AlarmBroadCastReceiver;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Static class for sending notification
 */
public class NotificationSender
{
	private static android.app.NotificationManager mNotifyManager;

	public static final int    NOTIFICATION_ID    = 0;
	public static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";


	private NotificationSender() {}


	/**
	 *
	 * @param ctx The application context
	 * @param time The time to send the notification
	 * @return A boolean if the notification was allowed to be sent
	 */
	public static boolean scheduleNotification(Context ctx, long time)
	{
		SharedPreferences sharedPref
				= ctx.getSharedPreferences(ctx.getString(R.string.preference_file),
				                           Context.MODE_PRIVATE);
		if ( sharedPref.getBoolean(ctx.getString(R.string.saved_alarms_allowed), false) )
		{
			AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
			Intent       intent       = new Intent(ctx, AlarmBroadCastReceiver.class);
			intent.setAction("android.alarm.receiver");

			int delay = sharedPref.getInt(ctx.getString(R.string.saved_alarm_delay), 0);
			PendingIntent pendingIntent
					= PendingIntent.getBroadcast(ctx, 100, intent, FLAG_CANCEL_CURRENT);
			alarmManager.set(AlarmManager.RTC_WAKEUP, time + ( delay * 1000 ), pendingIntent);
		}
		return sharedPref.getBoolean(ctx.getString(R.string.saved_alarms_allowed), false);
	}


	/**
	 * Sends a notification
	 * @param ctx The application context
	 */
	public static void sendNotification(Context ctx)
	{
		NotificationCompat.Builder notifyBuilder = getNotificationBuilder(ctx);
		mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
	}


	private static NotificationCompat.Builder getNotificationBuilder(Context ctx)
	{
		NotificationCompat.Builder notifyBuilder
				= new NotificationCompat.Builder(ctx, PRIMARY_CHANNEL_ID).setContentTitle(
				"Time For Meds!")
				                                                         .setContentText(
						                                                         "It is time for you to take your medication")
				                                                         .setSmallIcon(R.drawable.ic_alarm);
		return notifyBuilder;
	}


	/**
	 * Creates a notification channel
	 * @param ctx The application context
	 */
	public static void createNotificationChannel(Context ctx)
	{
		mNotifyManager
				= (android.app.NotificationManager) ctx.getSystemService(NOTIFICATION_SERVICE);
		if ( android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O )
		{
			NotificationChannel notificationChannel
					= new NotificationChannel(PRIMARY_CHANNEL_ID,
					                          "Mascot Notification",
					                          android.app.NotificationManager.IMPORTANCE_HIGH);
			notificationChannel.enableLights(true);
			notificationChannel.enableVibration(true);
			notificationChannel.setDescription("Notification from Pharmacy");
			mNotifyManager.createNotificationChannel(notificationChannel);
		}
	}
}
