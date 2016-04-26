package com.workshop.uitest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.json.JSONObject;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

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
       private int color = 0;
    private Vector<Route> activeRoutes = new Vector<>();
    private Vector<Buse> activeBuses = new Vector<>();
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
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                Boolean active = false;
                Object item = arg0.getItemAtPosition(arg2);
                if (item!=null&&!activeRoutes.isEmpty()) {
                    Toast.makeText(MapsActivity.this, item.toString(),
                            Toast.LENGTH_SHORT).show();
                    String test = item.toString();
                    for(int i = 0;i<activeRoutes.size();i++){
                        if(test.equals(activeRoutes.get(i).getName())){
                            active = true;
                            drawRoute(activeRoutes.get(i).getStrShape());
                            color = Color.parseColor(activeRoutes.get(i).getColor());
                            for(Buse bus:activeBuses){
                                if(bus.getRouteName().equals(test)){
                                    addBus(bus.getLatitude(),bus.getLongitude());
                                }
                            }
                        }

                    }
                    if(!active)
                        Toast.makeText(MapsActivity.this,"Route not active",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(MapsActivity.this, "Selected",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        test ();
    }

    /* -------------------------------------------------------------------------------------------*/
    private void test ()
    {
        final ArrayList<Integer> selecterRouts = new ArrayList<>();
        GetRoutes gr = new GetRoutes(new onTaskDone()
        {
            @Override
            public void ItIsDone(Vector<Route> routes)
            {
                for (int i=0; i < routes.size(); i++)
                {
                    if ((routes.get(i).isInService() == 1) && (routes.get(i).getStatus() == 1))
                    {
                        // Log.d(TAG, "ID              :" + routes.get(i).getID()           );
                        // Log.d(TAG, "Name            :" + routes.get(i).getName()         );
                        // Log.d(TAG, "Description     :" + routes.get(i).getDescription()  );
                        // Log.d(TAG, "Color           :" + routes.get(i).getColor()        );
                        // Log.d(TAG, "Shape           :" + routes.get(i).getShape()        );
                        // Log.d(TAG, "Status          :" + routes.get(i).getStatus()       );
                        // Log.d(TAG, "inService       :" + routes.get(i).isInService()     );
                        // Log.d(TAG, "url             :" + routes.get(i).getUrl()          );
                        // Log.d(TAG, "NextArrival     :" + routes.get(i).getNextArrival()  );
                        // Log.d(TAG, "Length          :" + routes.get(i).getLength()       );
                        // Log.d(TAG, "DepartureStop   :" + routes.get(i).getDepartureStop());
                        // Log.d(TAG, "NextDeparture   :" + routes.get(i).getNextDeparture());
                        // Log.d(TAG, "-------------------------------------------------------" );
                        activeRoutes.add(routes.get(i));
                        selecterRouts.add(routes.get(i).getID());
                    }
                }
                for(Integer id:selecterRouts){
                    testGetBuses(id);
                }

            }
            @Override
            public void onGotBus(Vector<Buse> buses) {/* Do nothing */ }
        });
        try
        {
            Log.d(TAG, "Sarting geting data from url");
            gr.execute(new URL("https://campusdata.uark.edu/api/routes") );
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }

    private void testGetBuses (int x)
    {
        // starting geting data for the buses that is on the routs
        GetBus gb = new GetBus(new onTaskDone()
        {
            @Override
            public void ItIsDone(Vector<Route> routes) {/* Do nothing */}

            @Override
            public void onGotBus(Vector<Buse> buses)
            {
                for (int i=0; i < buses.size(); i++)
                {
                    Log.d(TAG, "BUSS : ID              :" + buses.get(i).getId());
                    Log.d(TAG, "BUSS : Fleer           :" + buses.get(i).getFleer());
                    Log.d(TAG, "BUSS : Name            :" + buses.get(i).getName());
                    Log.d(TAG, "BUSS : Description     :" + buses.get(i).getDescription());
                    Log.d(TAG, "BUSS : Latitude        :" + String.valueOf(buses.get(i).getLatitude()));
                    Log.d(TAG, "-------------------------------------------------------" );
                    activeBuses.add(buses.get(i));
                }
            }
        });
        try
        {
            gb.execute(new URL("https://campusdata.uark.edu/api/buses?callback=?&routeIds=" + x));
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }
    /* -------------------------------------------------------------------------------------------*/

    private String getMapsApiDirectionsUrl(ArrayList<LatLng> pointSet) {


        String waypoints = "waypoints=optimize:true|";

        String points="";
        for(int i =0;i<pointSet.size()-2;i++){

            points = points+pointSet.get(i).latitude+","+pointSet.get(i).longitude+"|";
        }
        waypoints = waypoints+points;
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
            boolean check = routes.size()>0;
            if(check) {
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
                    polyLineOptions.color(color);
                }

                mMap.addPolyline(polyLineOptions);
            }
        }
    }
    private void addBus(double lat, double lng){
        LatLng bus = new LatLng(lat,lng);
        MarkerOptions options = new MarkerOptions();
        options.position(bus);
        mMap.addMarker(options);
    }
    private void drawRoute(String shape){

        ArrayList<LatLng> Shape = new ArrayList<>();
        ArrayList<LatLng> pointSet;
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




        LatLng UNION_STATION = new LatLng(36.067868, -94.176374);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UNION_STATION, 13));
//        addMarkers();

    }

    public void onFind(View view){
        Context context = getApplicationContext();
        CharSequence text = "FIND NEAREST BUS";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    public void onResetMap(View view){
        mMap.clear();
        Context context = getApplicationContext();
        CharSequence text = "RESET MAP";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
