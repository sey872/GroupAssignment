package com.example.scott.groupassignment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements AdapterView.OnItemClickListener {

    private Bundle bundle;
    private GoogleMap map;
    protected static boolean isMainShown = false;

    private List<storeList> store;

    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        store = new ArrayList<>();
        InputStream in;
        BufferedReader reader;
        String line;
        List<String> list = new ArrayList<>();
        Ratings storeRatings = new Ratings();
        int num = 0;

        final SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if(fm.getView() != null) {
            fm.getView().setVisibility(View.GONE);
        }

        // Getting Map for the SupportMapFragment
        map = fm.getMap();

        if (map != null) {

            // Enable MyLocation Button in the Map
            map.setMyLocationEnabled(true);
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            if (myLocation != null) {
                longitude = myLocation.getLongitude();
                latitude = myLocation.getLatitude();
            } else {
                latitude = -33.9859888;
                longitude = 151.090324;
            }
        }
        /*  ATTEMPT TO REMOVE MAP FRAGMENT RUNNING IN THE BACKGROUND
        android.app.Fragment fragment = this.getFragmentManager().findFragmentById(R.id.map);
        if (fragment != null) {
            android.app.FragmentTransaction ft = this.getFragmentManager().beginTransaction();
            ft.remove(fragment);
            ft.commit();
            map = null;
        }
        */
        
        try {
            in = this.getAssets().open("places.txt");
            reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
                list.add(line);
                String[] parts = line.split("~");
                //rating to display
                double finalRating = 0.0;
                //get list of ratings for store
                List<String> ratings = storeRatings.getRatings(parts[0]);
                //if store has ratings calculate the average
                if (ratings != null) {
                    finalRating = getRating(ratings);
                }

                System.out.println("TESTING: " + parts[4] + ", " + parts[3]);
                double dist = getDistance(longitude, latitude, Double.parseDouble(parts[4]), Double.parseDouble(parts[3]));

                store.add(new storeList(num++, parts[0], parts[1], dist, parts[2], finalRating, Double.parseDouble(parts[3]), Double.parseDouble(parts[4])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        sortList(store);

        ListView l = (ListView) findViewById(R.id.listView);

        ListAdapter change = new ListAdapter(this, R.layout.item_row, store);

        l.setAdapter(change);
        l.setOnItemClickListener(this);

        Button toMap = (Button) findViewById(R.id.tomap);

        final List<String> toPass = list;

        toMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                i.putStringArrayListExtra("stores", (ArrayList<String>) toPass);
                startActivity(i);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        FragmentManager FM = getFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FragmentDisplayStore F2 = new FragmentDisplayStore();

        bundle = new Bundle();
        bundle.putString("name", store.get(position).getName());
        bundle.putString("address", store.get(position).getAddress());
        bundle.putString("website", store.get(position).getWebsite());
        bundle.putDouble("rating", store.get(position).getRating());
        bundle.putDouble("lat", store.get(position).getLatitude());
        bundle.putDouble("long", store.get(position).getLongitude());

        F2.setArguments(bundle);

        if (getFragmentManager().findFragmentById(R.id.fr_display_store) == null) {
            View v1 = (View) findViewById(R.id.fadeBackground);
            v1.setVisibility(View.VISIBLE);
            FT.add(R.id.fr_display_store, F2);
            FT.addToBackStack("fr_store");
        }
        FT.commit();
    }

    private double getRating(List<String> rating)
    {
        double toReturn = 0.0;
        for(int i = 0; i < rating.size(); i++) {
            toReturn += Double.valueOf(rating.get(i));
        }
        toReturn = toReturn/rating.size();
        return toReturn;
    }

    private void sortList(List<storeList> list)
    {
        for(int i = 0; i < list.size(); i++)
        {
            for(int j = i; j < list.size(); j++)
            {
                if(list.get(i).getDistance() > list.get(j).getDistance())
                {
                    swap(list, i, j);
                }
            }
        }
    }

    private double getDistance(double x1, double y1, double x2, double y2)
    {
        double d2r = Math.PI / 180;
        double dLong = (x2 - x1) * d2r;
        double dLat = (y2 - y1) * d2r;
        double a = Math.pow(Math.sin(dLat / 2.0), 2) + Math.cos(y1 * d2r)
                * Math.cos(y2 * d2r) * Math.pow(Math.sin(dLong / 2.0), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6367000 * c;
        return (d/1000);
    }

    private void swap(List<storeList> list, int i, int j)
    {
        storeList temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
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
}