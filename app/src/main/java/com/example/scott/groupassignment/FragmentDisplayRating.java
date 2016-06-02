package com.example.scott.groupassignment;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scott on 5/5/2016.
 * For CSCI342 Assignment 2
 */
public class FragmentDisplayRating extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rate_store, container, false);

        v.setClickable(true);
        final List<Button> star = new ArrayList<>();

        star.add((Button) v.findViewById(R.id.onestar));
        star.add((Button) v.findViewById(R.id.twostar));
        star.add((Button) v.findViewById(R.id.threestar));
        star.add((Button) v.findViewById(R.id.fourstar));
        star.add((Button) v.findViewById(R.id.fivestar));

        for (int i = 0; i < star.size(); i++) {
            final int val = i;
            star.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < star.size(); j++) {
                        if (j <= val) {
                            star.get(j).setBackground(getResources().getDrawable(R.drawable.xbuttonback));
                        } else {
                            star.get(j).setBackground(getResources().getDrawable(R.drawable.starbuttonback));
                        }
                    }
                }
            });
        }

        Button xBut = (Button) v.findViewById(R.id.xbutton);

        xBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        v.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });

        return v;
    }
}
