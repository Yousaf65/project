package com.example.med_buddy;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {

    private String[] titles=new String[]{"Reminder","Appointments","Reports"};
    public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new reminders();

            case 1:
                return new appointment();

            case 2:
                return new notes();

        }
        return new notes();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
