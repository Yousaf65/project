package com.example.med_buddy;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        // Extract the data from the intent (e.g., medicine name)
        String medicineName = intent.getStringExtra("medicineName");

        // Perform the desired action (e.g., show a notification, play a sound)
        showToast(context, "It's time to take your medicine: " + medicineName);
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}

