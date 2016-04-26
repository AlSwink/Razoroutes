package com.workshop.uitest;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

/**
 * Created by borodin on 4/25/2016.
 */
public class GetBus extends AsyncTask<URL, Void, Vector<Buse>>
{
    private static final String TAG = "GetBus_TEST";
    private Vector<Buse> allBuses = null;
    private onTaskDone listener;

    public GetBus(onTaskDone listener) { this.listener = listener; }

    @Override
    protected Vector<Buse> doInBackground(URL... params)
    {
        Vector<Buse> buses = null;
        String finaleJson = getData(params[0]);
        Log.d(TAG, finaleJson);
        buses = getJArray (finaleJson);
        return buses;
    }

    @Override
    protected void onPreExecute()
    {
        allBuses = null;
    }

    @Override
    protected void onPostExecute(Vector<Buse> buses)
    {
        allBuses = buses;
        listener.onGotBus(allBuses);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private Vector<Buse> getJArray(String str)
    {
        Vector <Buse> buses = new Vector<>();
        int ID;
        int Fleer;
        String Name;
        String Description;
        int ZonarId;
        String GpsId;
        double Latitude;
        double Longitude;
        double Speed;
        String Heading;
        boolean Power;
        String Date;
        String Color;
        String RouteName;
        int RouteId;
        double Distance;
        String NextStop;
        String NextArrival;

        try
        {
            JSONArray array = new JSONArray(str);
            for (int i=0; i<array.length(); i++)
            {
                JSONObject row = array.getJSONObject(i);
                ID              = row.getInt("id");
                Fleer           = row.getInt("fleet");
                Name            = row.getString("name");
                Description     = row.getString("description");
                ZonarId         = row.getInt("zonarId");
                GpsId           = row.getString("gpsId");
                Latitude        = row.getDouble("latitude");
                Longitude       = row.getDouble("longitude");
                Speed           = row.getDouble("speed");
                Heading         = row.getString("heading");
                Power           = row.getBoolean("power");
                Date            = row.getString("date");
                Color           = row.getString("color");
                RouteName       = row.getString("routeName");
                RouteId         = row.getInt("routeId");
                Distance        = row.getDouble("distance");
                NextStop        = row.getString("nextStop");
                NextArrival     = row.getString("nextArrival");

                Buse b = new Buse(ID, Fleer, Name, Description, ZonarId, GpsId, Latitude, Longitude, Speed, Heading, Power, Date, Color, RouteName, RouteId, Distance, NextStop, NextArrival);
                buses.add(b);
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return buses;
    }
    private String getData(URL url)
    {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        try
        {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            String line = "";
            while ((line = reader.readLine()) != null)
            {
                line = line.replace("?([{", "[{");
                line = line.replace("}]);", "}]");
                buffer.append(line);
            }
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null) connection.disconnect();
            try
            {
                if (reader != null) reader.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }
}
