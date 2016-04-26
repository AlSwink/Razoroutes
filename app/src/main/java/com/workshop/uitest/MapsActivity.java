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
    private String selectedRoute;
    private String Green = "36.058506 -94.179786,36.058519 -94.180365,36.059204 -94.180346,36.059943 -94.180333,36.060626 -94.180301,36.061275 -94.180287,36.061877 -94.180271,36.062541 -94.18025,36.06279 -94.18022,36.063003 -94.180188,36.063102 -94.180204,36.063308 -94.180234,36.063827 -94.180212,36.06434 -94.180193,36.064922 -94.180158,36.06562 -94.180142,36.066246 -94.180118,36.066892 -94.180099,36.067419 -94.180089,36.0682 -94.180059,36.068839 -94.180038,36.069078 -94.179998,36.069371 -94.180022,36.0698 -94.18,36.070162 -94.179979,36.070241 -94.179939,36.070307 -94.179882,36.070368 -94.179794,36.070399 -94.179676,36.070386 -94.178845,36.070372 -94.177648,36.070374 -94.177192,36.070372 -94.176797,36.070372 -94.175735,36.070366 -94.175572,36.070345 -94.175518,36.070313 -94.1755,36.070277 -94.175502,36.070234 -94.175498,36.07019 -94.175505,36.070091 -94.175513,36.069857 -94.175542,36.069614 -94.175579,36.069429 -94.175589,36.069258 -94.175607,36.069082 -94.175631,36.068172 -94.175631,36.067716 -94.175636,36.067723 -94.175918,36.067724 -94.176126,36.067725 -94.176299,36.067732 -94.176404,36.067783 -94.176441,36.06789 -94.176449,36.067963 -94.176441,36.068007 -94.176429,36.068042 -94.176401,36.068053 -94.176345,36.068056 -94.176185,36.06805 -94.175924,36.068037 -94.175628,36.067653 -94.175636,36.067239 -94.175645,36.067062 -94.175698,36.066879 -94.175706,36.066647 -94.175698,36.066301 -94.175671,36.06593 -94.175628,36.065687 -94.17565,36.065631 -94.175811,36.065516 -94.175837,36.065149 -94.175805,36.064943 -94.175784,36.064781 -94.175746,36.064115 -94.175768,36.064015 -94.175776,36.063846 -94.175856,36.063705 -94.175805,36.063289 -94.175811,36.06315 -94.175813,36.063072 -94.175832,36.063061 -94.176213,36.063024 -94.176323,36.062463 -94.175993,36.06222 -94.175843,36.062086 -94.176047,36.061895 -94.176277,36.061676 -94.176422,36.061357 -94.176562,36.061134 -94.176605,36.060923 -94.176583,36.060726 -94.176537,36.060529 -94.176419,36.059943 -94.175961,36.05985 -94.17595,36.059126 -94.175945,36.0589 -94.175938,36.058716 -94.17591,36.05871 -94.175907,36.058631 -94.176444,36.058517 -94.177162,36.05848 -94.177511,36.058493 -94.178214,36.05851 -94.179024,36.058504 -94.179772";
    private String Blue = "36.067868 -94.176374,36.067992 -94.176356,36.068038 -94.176209,36.068031 -94.17568,36.068951 -94.175695,36.070195 -94.175512,36.07053 -94.175537,36.074009 -94.175468,36.076733 -94.175339,36.087631 -94.175007,36.087606 -94.173378,36.087681 -94.173266,36.087808 -94.173197,36.088339 -94.172929,36.088365 -94.172571,36.088313 -94.170581,36.079093 -94.170832,36.074661 -94.170993,36.070253 -94.1711,36.070314 -94.173347,36.070362 -94.175425,36.07032 -94.175588,36.067718 -94.175664,36.067731 -94.176175,36.067762 -94.176352,36.067862 -94.176379";
    private int color = 0;
    private Vector<Route> activeRoutes = new Vector<>();
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
                testGetBuses(selecterRouts.get(0));
                testGetBuses(selecterRouts.get(1));
                testGetBuses(selecterRouts.get(2));
                testGetBuses(selecterRouts.get(3));
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
                    Log.d(TAG, "-------------------------------------------------------" );
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
        LatLng bus = new LatLng(36.0604166,-94.1834819);
        // Add a marker in Sydney and move the camera
        MarkerOptions options = new MarkerOptions();
        options.position(bus);
//        options.position(MEADOW);
//        options.position(REID_HALL);
        mMap.addMarker(options);



        String shape = "36.067868 -94.176374,36.067992 -94.176356,36.068038 -94.176209,36.068031 -94.17568,36.068951 -94.175695,36.070195 -94.175512,36.07053 -94.175537,36.074009 -94.175468,36.076733 -94.175339,36.087631 -94.175007,36.087606 -94.173378,36.087681 -94.173266,36.087808 -94.173197,36.088339 -94.172929,36.088365 -94.172571,36.088313 -94.170581,36.079093 -94.170832,36.074661 -94.170993,36.070253 -94.1711,36.070314 -94.173347,36.070362 -94.175425,36.07032 -94.175588,36.067718 -94.175664,36.067731 -94.176175,36.067762 -94.176352,36.067862 -94.176379";

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
