package com.example.scott.groupassignment;

import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        /*mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                // TODO Auto-generated method stub
                System.out.println("TEST");
                mMap.addMarker(new MarkerOptions().position(point).title(
                        point.toString()));
            }
        });*/
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
//Get the text file
        //File file = new File("assets/places.txt");
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-33.867, 151.206)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-33.863942,151.21192)).title("Bar Luca").snippet("Website: http://barluca.com.au/").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        /*String tempName = "";
        String tempWeb = "";
        double tempLat, tempLong;
        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new FileReader(file));
            String temp = "";
            while (true)
            {
                temp = in.readLine();
                tempName = temp;
                temp = in.readLine();
                tempWeb = temp;
                temp = in.readLine();
                String[] str_array = temp.split(":");
                tempLat = Double.parseDouble(str_array[0]);
                tempLong = Double.parseDouble(str_array[1]);
                System.out.println("GABERLAD= " + tempName + " : " + tempWeb + " : " + tempLat + "," + tempLong);

                mMap.addMarker(new MarkerOptions().position(new LatLng(tempLat,tempLong)).title(tempName).snippet("Website: " + tempWeb).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                in.readLine();
                if (temp == null) break;
            }
        } catch (IOException e) {
            System.out.println("There was a problem: " + e);
            e.printStackTrace();
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (Exception e)
            {

            }
        }*/
        // Add a marker in Sydney and move the camera
        //mMap.addMarker(new MarkerOptions().position(new LatLng(-33.867, 151.206)).title("Marker in Sydney").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        //mMap.addMarker(new MarkerOptions().position(new LatLng()).title("Melbourne").snippet("Website: " + tempWeb).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));


    }
}
