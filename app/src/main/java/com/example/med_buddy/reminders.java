package com.example.med_buddy;
import android.app.AlarmManager;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Calendar;

public class reminders extends Fragment {

    private static final int ADD_MEDICINE_REQUEST_CODE = 1;

    private ListView medicineListView1;
    private Button addMedicineButton1;

    private ArrayList<MedicineReminder> medicineList;
    private MedicineReminderAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reminders, container, false);

        medicineList = new ArrayList<>();
        adapter = new MedicineReminderAdapter(getActivity(), medicineList);
        adapter.setDeleteButtonClickListener(new MedicineReminderAdapter.DeleteButtonClickListener() {
            @Override
            public void onDeleteButtonClick(int position) {
                showDeleteConfirmationDialog(position);
            }
        });

        medicineListView1 = view.findViewById(R.id.medicineListView);
        addMedicineButton1 = view.findViewById(R.id.addMedicineButton);

        medicineListView1.setAdapter(adapter);

        medicineListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MedicineReminder medicineReminder = adapter.getItem(position);
                // Handle item click (e.g., show details)
            }
        });

        addMedicineButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), add_medicines.class);
                startActivityForResult(intent, ADD_MEDICINE_REQUEST_CODE);
            }
        });

        return view;
    }

    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete Reminder")
                .setMessage("Are you sure you want to delete this reminder?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        medicineList.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    // Inside the onActivityResult() method in reminders.java
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_MEDICINE_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            String medicineName = data.getStringExtra("medicineName");
            int hour = data.getIntExtra("hour", 0);
            int minute = data.getIntExtra("minute", 0);
            ArrayList<Integer> selectedDates = data.getIntegerArrayListExtra("selectedDates");

            MedicineReminder medicineReminder = new MedicineReminder(medicineName, hour, minute, selectedDates);
            medicineList.add(medicineReminder);
            adapter.notifyDataSetChanged();

            // Schedule the alarm for the medicine reminder
            scheduleAlarm(medicineReminder);
        }
    }

    // Add the following method to the reminders.java class

    private void scheduleAlarm(MedicineReminder medicineReminder) {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getActivity(), ReminderBroadcastReceiver.class);
        intent.putExtra("medicineName", medicineReminder.getMedicineName());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, medicineReminder.getHour());
        calendar.set(Calendar.MINUTE, medicineReminder.getMinute());
        calendar.set(Calendar.SECOND, 0);

        // Set the alarm to repeat daily (you can customize this based on your needs)
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

}
