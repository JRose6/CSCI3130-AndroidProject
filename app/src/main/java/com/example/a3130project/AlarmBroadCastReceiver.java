package com.example.a3130project;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.*;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmBroadCastReceiver extends BroadcastReceiver {
    String TAG = "AlarmReceiver";
    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"::Received");
        if(intent!=null){
            NotificationSender.sendNotification(context);
            NotificationSender.scheduleNotification(context,System.currentTimeMillis()+(60*1000));
        }
    }
}
