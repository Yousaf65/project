package com.example.med_buddy;


// AddMedicineActivity.java
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.ArrayList;

public class add_medicines extends Activity {

    private EditText medicineNameEditText1;
    private TimePicker timePicker;
    private Button addMedicineButton;

    private ArrayList<Integer> selectedDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicines);

        selectedDates = new ArrayList<>();

        medicineNameEditText1 = findViewById(R.id.Edittext1);
        timePicker = findViewById(R.id.timePicker);
        addMedicineButton = findViewById(R.id.addMedicineButton);

        addMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medicineName = medicineNameEditText1.getText().toString();
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                // Get selected dates logic (e.g., from a calendar view or checkbox selection)
                // Add the selected dates to the selectedDates ArrayList

                // Return the data back to MainActivity
                Intent intent = new Intent();
                intent.putExtra("medicineName", medicineName);
                intent.putExtra("hour", hour);
                intent.putExtra("minute", minute);
                intent.putIntegerArrayListExtra("selectedDates", selectedDates);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
