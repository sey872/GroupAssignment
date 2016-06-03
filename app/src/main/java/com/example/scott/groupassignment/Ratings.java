package com.example.scott.groupassignment;

/**
 * Created by Scott on 3/6/2016.
 * For CSCI342 Assignment 2
 */
import android.app.Fragment;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by Scott on 21/5/2016.
 * For CSCI342 Assignment 2
 */
public class Ratings {
    public String startUrl;
    private List<ratingArray> store;
    private String toGet;

    private class ratingArray
    {
        private List<String> rating;
        private String storeName;

        public ratingArray()
        {
            rating = new ArrayList<>();
        }
        public ratingArray(String storeName)
        {
            this.storeName = storeName;
            rating = new ArrayList<>();
        }
        public void addRating(String newRating)
        {
            rating.add(newRating);
        }
        public List<String> getRating() {
            return rating;
        }

        public String getStoreName() {
            return storeName;
        }
    }

    public Ratings()
    {
        toGet = "all";
        startUrl = "https://api.myjson.com/bins/3mloq";

        store = new ArrayList<>();
        performNASARequest("whatever");
        //ratings.add(testJSON("bob"));
    }

    public List<String> getRatings(String storeName)
    {
        for(int i = 0; i < store.size(); i++)
        {
            if(store.get(i).getStoreName().equals(storeName))
            {
                return store.get(i).getRating();
            }
        }
        return null;
    }

    public String testJSON(String storeName)
    {
        String jsonStr = "{\"store\":[{\"name\":\"bob\",\"rating\":\"2.5\"}, {\"name\":\"ded\",\"rating\":\"4.5\"}]}";
        String rating = "failed";

        JSONArray jArray = null;
        try {
            JSONObject jsonStart =  new JSONObject(jsonStr);

            jArray = jsonStart.getJSONArray("store");
            for (int i = 0; i < jArray.length(); i++)
            {
                JSONObject jstore = jArray.getJSONObject(i);
                String jName = jstore.getString("name");
                rating = jstore.getString("rating");

                System.out.println("Name: " + jName);
                System.out.println("Rating: " + rating);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rating;
    }

    public String performNASARequest(String storeName)
    {
        String rating = null;
        try {
            rating = new HTMLNetControl().execute(storeName).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return rating;
    }

    public class HTMLNetControl extends AsyncTask<String, Integer, String> {

        private String urlname;

        @Override
        protected void onPreExecute() {
            Log.i("Async-Example", "onPreExecute Called");
        }

        @Override
        protected String doInBackground(String... params) {
            String test = getRating();
            return test;
        }

        private String getRating()
        {
            HttpURLConnection connection = null;
            StringBuilder stringBuilder = new StringBuilder(startUrl);

            String rating = "0.0";

            try {
                URL url = new URL(stringBuilder.toString());

                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setReadTimeout(15 * 1000);
                connection.setConnectTimeout(30 * 1000);
                connection.setDoInput(true);
                connection.connect();
                int response = connection.getResponseCode();
                if (response == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    stringBuilder = new StringBuilder();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }
                    System.out.print(stringBuilder.toString());

                    JSONArray jArray = null;
                    try {
                        JSONObject jsonStart =  new JSONObject(stringBuilder.toString());

                        jArray = jsonStart.getJSONArray("store");
                        for (int i = 0; i < jArray.length(); i++)
                        {
                            JSONObject jstore = jArray.getJSONObject(i);
                            String jName = jstore.getString("name");

                            store.add(new ratingArray(jName));

                            JSONArray jRatings = jstore.getJSONArray("rating");
                            for (int j = 0; j < jRatings.length(); j++) {
                                JSONObject jPickles = jRatings.getJSONObject(j);
                                store.get(i).addRating(jPickles.getString("pickles"));
                            }
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return rating;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Async-Example", "onPostExecute Called");
        }
    }
}
