package com.example.scott.groupassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Scott on 8/5/2016.
 * For CSCI342 Assignment 2
 */
public class ListAdapter extends ArrayAdapter<storeList> {

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, List<storeList> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_row, null);
        }

        storeList p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.row_name);
            TextView tt2 = (TextView) v.findViewById(R.id.row_distance);

            if (tt1 != null) {
                tt1.setText(p.getName());
            }

            if (tt2 != null) {
                DecimalFormat df = new DecimalFormat("#.###");
                tt2.setText(String.valueOf(df.format(p.getDistance())) + "KM");
            }
        }
        return v;
    }
}