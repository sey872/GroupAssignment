package com.example.scott.groupassignment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Scott on 5/5/2016.
 * For CSCI342 Assignment 2
 */
public class FragmentDisplayStore extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_display_store, container, false);

        TextView tt1 = (TextView) v.findViewById(R.id.store_name);
        TextView tt2 = (TextView) v.findViewById(R.id.store_website);
        TextView tt3 = (TextView) v.findViewById(R.id.store_pickles);

        if (tt1 != null) {
            tt1.setText(getArguments().getString("name"));
        }
        if (tt2 != null) {
            tt2.setText(getArguments().getString("website"));
        }
        if (tt3 != null) {
            tt3.setText("Pickles: " + String.valueOf(getArguments().getDouble("rating")) + "/5");
        }
        return v;
    }
}
