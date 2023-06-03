package com.example.med_buddy;



import java.util.ArrayList;

// MedicineReminder.java
public class MedicineReminder {

    private String medicineName;
    private int hour;
    private int minute;
    private ArrayList<Integer> selectedDates;

    public MedicineReminder(String medicineName, int hour, int minute, ArrayList<Integer> selectedDates) {
        this.medicineName = medicineName;
        this.hour = hour;
        this.minute = minute;
        this.selectedDates = selectedDates;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public ArrayList<Integer> getSelectedDates() {
        return selectedDates;
    }
}

