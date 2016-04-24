package com.workshop.uitest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
//route drawing code taken from
//http://javapapers.com/android/draw-path-on-google-maps-android-api/

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    final String TAG = "PathGoogleMapActivity";

    //String output = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Spinner spinner = (Spinner) findViewById(R.id.route_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.route_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener());
        



       // new JSONTask(output).execute("https://campusdata.uark.edu/api/buses?callback=?&2");
        //Log.i(TAG,output);
    }
    private String getMapsApiDirectionsUrl(ArrayList<LatLng> pointSet) {


        String waypoints = "waypoints=optimize:true|";

        String points="";
        for(int i =0;i<pointSet.size()-2;i++){

            points = points+pointSet.get(i).latitude+","+pointSet.get(i).longitude+"|";
        }
        waypoints = waypoints+points;
        Log.i(TAG,waypoints);
        String sensor = "sensor=false";
        //String params = waypoints + "&" + sensor;
        String origin = "origin=" + pointSet.get(pointSet.size()-2).latitude + "," + pointSet.get(pointSet.size()-2).longitude;
        String destination = "destination=" + pointSet.get(pointSet.size()-1).latitude + "," + pointSet.get(pointSet.size()-1).longitude;
        String params = origin + "&" + destination + "&" + waypoints + "&" + sensor;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/"
                + output + "?" + params;
        return url;
    }

//    private void addMarkers() {
//        if (mMap != null) {
//            mMap.addMarker(new MarkerOptions().position(UNION_STATION)
//                    .title("First Point"));
//            mMap.addMarker(new MarkerOptions().position(MEADOW)
//                    .title("Second Point"));
//            mMap.addMarker(new MarkerOptions().position(REID_HALL)
//                    .title("Third Point"));
//        }
//    }

    private class ReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... url) {
            String data = "";
            try {
                HttpConnection http = new HttpConnection();
                data = http.readUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            new ParserTask().execute(result);
        }
    }

    private class ParserTask extends
            AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(
                String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                PathJSONParser parser = new PathJSONParser();
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
            ArrayList<LatLng> points = null;
            PolylineOptions polyLineOptions = null;

            // traversing through routes
            Log.i(TAG, String.valueOf(routes.size()));
            for (int i = 0; i < routes.size(); i++) {
                points = new ArrayList<LatLng>();
                polyLineOptions = new PolylineOptions();
                List<HashMap<String, String>> path = routes.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                polyLineOptions.addAll(points);
                polyLineOptions.width(5);
                polyLineOptions.color(Color.BLUE);
            }

            mMap.addPolyline(polyLineOptions);
        }
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
        LatLng bus = new LatLng(36.0604166,-94.1834819);
        // Add a marker in Sydney and move the camera
        MarkerOptions options = new MarkerOptions();
        options.position(bus);
//        options.position(MEADOW);
//        options.position(REID_HALL);
        mMap.addMarker(options);


        ArrayList<LatLng> Shape = new ArrayList<>();
        ArrayList<LatLng> pointSet;
        String shape = "36.067868 -94.176374,36.067992 -94.176356,36.068038 -94.176209,36.068031 -94.17568,36.068951 -94.175695,36.070195 -94.175512,36.07053 -94.175537,36.074009 -94.175468,36.076733 -94.175339,36.087631 -94.175007,36.087606 -94.173378,36.087681 -94.173266,36.087808 -94.173197,36.088339 -94.172929,36.088365 -94.172571,36.088313 -94.170581,36.079093 -94.170832,36.074661 -94.170993,36.070253 -94.1711,36.070314 -94.173347,36.070362 -94.175425,36.07032 -94.175588,36.067718 -94.175664,36.067731 -94.176175,36.067762 -94.176352,36.067862 -94.176379";
        String[] pairs = shape.split(",");
        for(int i=0;i<pairs.length;i++){
            String[]pair = pairs[i].split(" ");
            double lat = Double.parseDouble(pair[0]);
            double lng = Double.parseDouble(pair[1]);
            LatLng latLng = new LatLng(lat,lng);
            Shape.add(latLng);
        }
        int counter = 0;
        while(counter<Shape.size()){
            pointSet = new ArrayList<>();
            int subcounter = 0;
            for(int i =0;i<8;i++){
                if(i+counter<Shape.size()){
                pointSet.add(Shape.get(i+counter));
                }
                subcounter++;
            }
            pointSet.add(pointSet.get(0));
            pointSet.add(pointSet.get(pointSet.size()-1));
            counter+=subcounter;
            counter--;

            String url = getMapsApiDirectionsUrl(pointSet);
            ReadTask downloadTask = new ReadTask();
            Log.i(TAG,url);
            downloadTask.execute(url);
        }
        LatLng UNION_STATION = new LatLng(36.067868, -94.176374);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UNION_STATION, 13));
//        addMarkers();

    }

}
