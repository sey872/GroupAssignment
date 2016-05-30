package com.example.scott.groupassignment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static boolean isHelpShown = false;
    protected static boolean isInfoShown = false;
    protected static boolean isMainShown = false;
    private static boolean isViewShown = false;

    private List<storeList> test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = new ArrayList<>();

        test.add(new storeList(0, "Bob's Burgers", 2.4));
        test.add(new storeList(1, "Bob's Burgers", 2.5));
        test.add(new storeList(2, "Bob's Burgers", 2.6));
        test.add(new storeList(3, "Bob's Burgers", 2.7));

        ListView l = (ListView) findViewById(R.id.listView);

        ListAdapter change = new ListAdapter(this, R.layout.item_row, test);

        l.setAdapter(change);
        l.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        FragmentManager FM = getFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FragmentDisplayStore F2 = new FragmentDisplayStore();

        Bundle bundle = new Bundle();
        bundle.putString("name", test.get(position).getName());
        F2.setArguments(bundle);

        if(getFragmentManager().findFragmentById(R.id.fr_display_store) == null)
        {
            FT.add(R.id.fr_display_store, F2);
            FT.addToBackStack("fr_store");
        }
        FT.commit();
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
            if (isMainShown) {
                finish();
            } else  {

            }

        } else {
            super.onBackPressed();
        }
    }
}
