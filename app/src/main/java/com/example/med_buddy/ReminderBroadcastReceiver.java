package com.example.med_buddy;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReminderBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String medicineName = intent.getStringExtra("medicineName");
        // Perform the desired action when the alarm triggers
        // For example, show a notification, play a sound, or start an activity
        Toast.makeText(context, "Reminder: It's time to take " + medicineName, Toast.LENGTH_SHORT).show();
    }
}



