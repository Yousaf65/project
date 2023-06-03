package com.example.med_buddy;




// MedicineReminderAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MedicineReminderAdapter extends ArrayAdapter<MedicineReminder> {

    private LayoutInflater inflater;
    private DeleteButtonClickListener deleteButtonClickListener;

    public interface DeleteButtonClickListener {
        void onDeleteButtonClick(int position);
    }

    public void setDeleteButtonClickListener(DeleteButtonClickListener listener) {
        this.deleteButtonClickListener = listener;
    }

    public MedicineReminderAdapter(Context context, ArrayList<MedicineReminder> medicineReminders) {
        super(context, 0, medicineReminders);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.activity_list_item_medicine, parent, false);
        }

        MedicineReminder medicineReminder = getItem(position);

        TextView medicineNameTextView = view.findViewById(R.id.medicineNameTextView);
        TextView medicineTimeTextView = view.findViewById(R.id.medicineTimeTextView);
        Button deleteButton = view.findViewById(R.id.deleteButton);

        medicineNameTextView.setText(medicineReminder.getMedicineName());
        String time = String.format("%02d:%02d", medicineReminder.getHour(), medicineReminder.getMinute());
        medicineTimeTextView.setText("Time: " + time);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteButtonClickListener != null) {
                    deleteButtonClickListener.onDeleteButtonClick(position);
                }
            }
        });

        return view;
    }
}



