package com.example.scott.groupassignment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Scott on 5/5/2016.
 * For CSCI342 Assignment 2
 */
public class FragmentDisplayStore extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_display_store, container, false);

        TextView tt1 = (TextView) v.findViewById(R.id.store_name);
        TextView tt2 = (TextView) v.findViewById(R.id.store_website);
        TextView tt3 = (TextView) v.findViewById(R.id.store_pickles);
        ImageView mapImage = (ImageView) v.findViewById(R.id.store_image);

        String url = "http://maps.google.com/maps/api/staticmap?center=" + getArguments().getDouble("lat") + "," + getArguments().getDouble("long") + "&zoom=14&markers=" + getArguments().getDouble("lat") + "," + getArguments().getDouble("long")+ "&size=250x188&sensor=false";
        new DownloadImageTask(mapImage).execute(url);


        if (tt1 != null) {
            tt1.setText(getArguments().getString("name"));
        }
        if (tt2 != null) {
            tt2.setText(getArguments().getString("website"));
        }
        if (tt3 != null) {
            tt3.setText("Pickles: " + String.valueOf(getArguments().getDouble("rating")) + "/5");
        }

        Button xBut = (Button) v.findViewById(R.id.xbutton);

        xBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
                View v1 = (View) getActivity().findViewById(R.id.fadeBackground);
                v1.setVisibility(View.GONE);
            }
        });


        Button rateBut = (Button) v.findViewById(R.id.ratebutton);

        rateBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager FM = getFragmentManager();
                FragmentTransaction FT = FM.beginTransaction();
                FragmentDisplayRating F2 = new FragmentDisplayRating();

                if (getFragmentManager().findFragmentById(R.id.fr_display_rating) == null) {
                    FT.add(R.id.fr_display_rating, F2);
                    FT.addToBackStack("fr_rating");
                }
                FT.commit();
            }
        });
        return v;
    }

    private class DownloadImageTask extends AsyncTask<String,Void,Bitmap> {

        ImageView imageView;

        public DownloadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}
