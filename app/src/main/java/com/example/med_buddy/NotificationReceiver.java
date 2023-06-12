package com.example.med_buddy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String task = intent.getStringExtra("task");
        if (task != null) {
            showNotification(context, task);
            broadcastTaskAdded(context, task);
        }
    }

    private void showNotification(Context context, String task) {
        // Implement your notification logic here
        Toast.makeText(context, "Reminder: " + task, Toast.LENGTH_SHORT).show();
    }

    private void broadcastTaskAdded(Context context, String task) {
        Intent broadcastIntent = new Intent("com.example.reminderapp.ACTION_REMINDER");
        broadcastIntent.putExtra("task", task);
        context.sendBroadcast(broadcastIntent);
    }
}
