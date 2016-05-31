package com.example.scott.groupassignment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends FragmentActivity implements AdapterView.OnItemClickListener {

    private Bundle bundle;
    GoogleMap map;
    ArrayList<LatLng> markerPoints;
    TextView tvDistanceDuration;

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

        Button toMap = (Button) findViewById(R.id.tomap);

        toMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapView.class);
                startActivity(i);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

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
        });*/
    }

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
                    HashMap<String, String> point = path.get(j);

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
            // Drawing polyline in the Google Map for the i-th route
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

        /**
         * A method to download json data from url
         */
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
    }
}
