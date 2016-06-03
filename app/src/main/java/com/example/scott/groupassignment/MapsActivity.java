package com.example.scott.groupassignment;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<storeList> store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_view);

        ArrayList<String> pass = getIntent().getStringArrayListExtra("stores");

        store = new ArrayList<>();

        for(int i = 0; i < pass.size(); i++)
        {
            String[] parts = pass.get(i).split("~");
            store.add(new storeList(i, parts[0], parts[1], 2.4, parts[2], 4.5, Double.parseDouble(parts[3]), Double.parseDouble(parts[4])));
        }

        Button toList = (Button) findViewById(R.id.tolist);

        toList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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

        mMap = googleMap;

        /*  MY LOCATION ATTEMPT

        mMap.setMyLocationEnabled(true);

        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

            @Override
            public void onMyLocationChange(Location arg0) {
                // TODO Auto-generated method stub
                mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("It's Me!").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            }
        });*/

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-33.867, 151.206)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-33.867, 151.206), 10.0f));
        for(int i = 0; i < store.size(); i++)
        {
            mMap.addMarker(new MarkerOptions().position(new LatLng(store.get(i).getLatitude(), store.get(i).getLongitude())).title(store.get(i).getName()).snippet(store.get(i).getWebsite()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }
        //Zomato,https://www.zomato.com/sydney/restaurants/burger?page=3http://itouchmap.com/latlong.html
    }
}
