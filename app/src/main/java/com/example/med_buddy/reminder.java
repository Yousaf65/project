package com.example.med_buddy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class reminder extends Fragment {
    private FloatingActionButton mCreateRem;
    private RecyclerView mRecyclerview;
    private ArrayList<Model> dataholder = new ArrayList<>(); // Array list to add reminders and display in recyclerview
    private myAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reminders, container, false);

        mRecyclerview = rootView.findViewById(R.id.recyclerView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCreateRem = rootView.findViewById(R.id.create_reminder); // Floating action button to change activity
        mCreateRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReminderActivity.class);
                startActivity(intent); // Starts the new activity to add Reminders
            }
        });

        Cursor cursor = new dbManager(getActivity()).readallreminders(); // Cursor to load data from the database
        while (cursor.moveToNext()) {
            Model model = new Model(cursor.getString(1), cursor.getString(2), cursor.getString(3));
            dataholder.add(model);
        }

        adapter = new myAdapter(dataholder);
        mRecyclerview.setAdapter(adapter); // Binds the adapter with recyclerview

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRecyclerview.setAdapter(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.dataholder.clear();
            adapter.notifyDataSetChanged();
        }
    }

}
