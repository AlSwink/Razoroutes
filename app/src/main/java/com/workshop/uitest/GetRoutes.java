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
public class GetRoutes extends AsyncTask <URL, Void, Vector<Route>>
{
    private static final String TAG = "GetRoutes_TEST";
    private Vector<Route> allRoutes=null;
    private onTaskDone listener;

    public GetRoutes(onTaskDone listener)
    {
        this.listener = listener;
    }

    @Override
    protected Vector<Route> doInBackground(URL... params)
    {
        Vector <Route> routes = null;
        String finalJson = getData(params[0]);
        Log.d(TAG, finalJson);
        routes = getJArray (finalJson);
        return routes;
    }

    @Override
    protected void onPostExecute(Vector<Route> routes)
    {
        allRoutes = routes;
        listener.ItIsDone(allRoutes);
    }

    @Override
    protected void onPreExecute()
    {
        allRoutes = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public Vector<Route> getAllRoutes()
    {
        return allRoutes;
    }

    private Vector<Route> getJArray (String str)
    {
        Vector <Route> routes = new Vector<>();
        int     ID;
        String  Name;
        String  Description;
        String  Color;
        String  Shape;
        int Status;
        int inService;
        String  url;
        String  NextArrival;
        Double  Length;
        int     DepartureStop;
        String  NextDeparture;
        try
        {
            JSONArray array = new JSONArray(str);
            for (int i=0; i<array.length(); i++)
            {
                JSONObject row = array.getJSONObject(i);
                ID              = row.getInt("id");
                Name            = row.getString("name");
                Description     = row.getString("description");
                Color           = row.getString("color");
                Shape           = row.getString("shape");
                Status          = row.getInt("status");
                inService       = row.getInt("inService");
                url             = row.getString("url");
                NextArrival     = row.getString("nextArrival");
                Length          = row.getDouble("length");
                DepartureStop   = row.getInt("departureStop");
                NextDeparture   = row.getString("nextDeparture");

                Route route = new Route(ID, Name, Description, Color, Shape, Status, inService, url, NextArrival, Length, DepartureStop, NextDeparture);
                routes.add(route);
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return routes;
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
