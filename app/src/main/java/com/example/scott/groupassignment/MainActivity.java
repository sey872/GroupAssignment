package com.example.scott.groupassignment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static boolean isHelpShown = false;
    protected static boolean isInfoShown = false;
    protected static boolean isMainShown = false;
    private static boolean isViewShown = false;

    private List<storeList> test;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = new ArrayList<>();

        test.add(new storeList(0, "Bob's Burgers", 2.4, "www.bobislife.com", 4.5));
        test.add(new storeList(1, "Your burgers", 2.5, "www.yourburgers.com", 3.8));
        test.add(new storeList(2, "Real Burgers", 2.6, "www.thisisarealsite.com", 1.1));
        test.add(new storeList(3, "Big snacks", 2.7, "www.bigsnacks.com", 3.2));

        //testing lots of data
        for(int i = 0; i < 50; i++)
        {
            test.add(new storeList(0, "Dummy Data", 3.4+i, "www.bedsite.com", 1));
        }

        ListView l = (ListView) findViewById(R.id.listView);

        ListAdapter change = new ListAdapter(this, R.layout.item_row, test);

        l.setAdapter(change);
        l.setOnItemClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private Bundle bundle;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        FragmentManager FM = getFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FragmentDisplayStore F2 = new FragmentDisplayStore();

        bundle = new Bundle();
        bundle.putString("name", test.get(position).getName());
        bundle.putString("website", test.get(position).getWebsite());
        bundle.putDouble("rating", test.get(position).getRating());
        F2.setArguments(bundle);

        if (getFragmentManager().findFragmentById(R.id.fr_display_store) == null) {
            View v1 = (View) findViewById(R.id.fadeBackground);
            v1.setVisibility(View.VISIBLE);
            FT.add(R.id.fr_display_store, F2);
            FT.addToBackStack("fr_store");
        }
        FT.commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() != 0) {
            View v1 = (View) findViewById(R.id.fadeBackground);
            v1.setVisibility(View.GONE);
            getFragmentManager().popBackStack();
            if (isMainShown) {
                finish();
            } else {

            }

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.scott.groupassignment/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.scott.groupassignment/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
