package com.example.scott.groupassignment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
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
    private ArrayList<LatLng> markerPoints;
    private TextView tvDistanceDuration;
    protected static boolean isMainShown = false;
    private LocationManager locationManager;
    private LocationListener locationListener;

    private List<storeList> store;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
        try {
            in = this.getAssets().open("places.txt");
            reader = new BufferedReader(new InputStreamReader(in));
            while((line = reader.readLine()) != null)
            {
                list.add(line);
                String[] parts = line.split(",");

                //rating to display
                double finalRating = 0.0;

                //get list of ratings for store
                List<String> ratings = storeRatings.getRatings(parts[0]);

                //if store has ratings calculate the average
                if(ratings != null) {
                    finalRating = getRating(ratings);
                }

                store.add(new storeList(num++, parts[0], 3.4, parts[1], finalRating, Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        /*locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            public void onProviderDisabled(String s){

            }
        };//if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {

        }
        else
        {
            locationManager.requestLocationUpdates("gps", 10000, 0, (android.location.LocationListener) locationListener);
        }*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    private void swap(List<storeList> list, int i, int j)
    {
        storeList temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

        /*tvDistanceDuration = (TextView) findViewById(R.id.tv_distance_time);
        markerPoints = new ArrayList<LatLng>();// Initializing

        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map); //Getting reference to SupportMapFragment of the activity_main
        map = fm.getMap();//Getting Map for the SupportMapFragment

        // Enable MyLocation Button in the Map
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);

        // Setting onclick event listener for the map
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                // Already two locations
                if (markerPoints.size() > 1) {
                    markerPoints.clear();
                    map.clear();
                }
                markerPoints.add(point); //Adding new item to the ArrayList
                MarkerOptions options = new MarkerOptions(); //Creating MarkerOptions
                options.position(point); //Setting the position of the marker

                if (markerPoints.size() == 1) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                } else if (markerPoints.size() == 2) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }

                map.addMarker(options);//Add new marker to the Google Map Android API V2

                // Checks, whether start and end locations are captured
                if (markerPoints.size() >= 2) {
                    LatLng origin = markerPoints.get(0);
                    LatLng dest = markerPoints.get(1);
                    String url = getDirectionsUrl(origin, dest); // Getting URL to the Google Directions API

                    DownloadTask downloadTask = new DownloadTask();
                    downloadTask.execute(url);// Start downloading json data from Google Directions API
                }
            }
        });HashMap<String, String> point = path.get(j);

                    if (j == 0) {    // Get distance from the list
                        distance = (String) point.get("distance");
                        continue;
                    } else if (j == 1) { // Get duration from the list
                        duration = (String) point.get("duration");
                        continue;
                    }

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }
                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(2);
                lineOptions.color(Color.RED);
            }
            tvDistanceDuration.setText("Distance:" + distance + ", Duration:" + duration);
            // Drawing


    */@Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        FragmentManager FM = getFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FragmentDisplayStore F2 = new FragmentDisplayStore();

        bundle = new Bundle();
        bundle.putString("name", store.get(position).getName());
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
    }/*

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
        //client.connect();
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

    public class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            String duration = "";

            if (result.size() < 1) {
                Toast.makeText(getBaseContext(), "No Points", Toast.LENGTH_SHORT).show();
                return;
            }

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    polyline in the Google Map for the i-th route
            map.addPolyline(lineOptions);
        }
    }

    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {
            String data = ""; //For storing data from web service
            try {
                data = downloadUrl(url[0]);// Fetching the data from web service
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            MainActivity.ParserTask parserTask = new MainActivity.ParserTask();
            parserTask.execute(result); //Invokes the thread for parsing the JSON data
        }
        private String downloadUrl(String strUrl) throws IOException {
            String data = "";
            InputStream iStream = null;
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(strUrl);
                urlConnection = (HttpURLConnection) url.openConnection();// Creating an http connection to communicate with url
                urlConnection.connect();// Connecting to url
                iStream = urlConnection.getInputStream();// Reading data from url
                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
                StringBuffer sb = new StringBuffer();

                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                data = sb.toString();
                br.close();

            } catch (Exception e) {
                Log.d("Exception downloading", e.toString());
            } finally {
                iStream.close();
                urlConnection.disconnect();
            }
            return data;
        }
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        String str_origin = "origin=" + origin.latitude + "," + origin.longitude; // Origin of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude; // Destination of route
        String sensor = "sensor=false"; // Sensor enabled
        String parameters = str_origin + "&" + str_dest + "&" + sensor; // Building the parameters to the web service
        String output = "json";// Output format
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters; // Building the url to the web service
        return url;
    }*/
}
