package com.workshop.uitest;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Alan Swink on 4/18/2016.
 */
class RoutesJSON extends AsyncTask<String, String, ArrayList<Route>>{

    ArrayList<Route> output = new ArrayList<>();

    RoutesJSON(ArrayList<Route> r){output = r;}


    private final static String TAG = "RoutesJSON";
    
    protected ArrayList<Route> doInBackground(String...params){

        ArrayList<Route> routes = new ArrayList<>();
        String[] JSONArray = getdata(params[0]);
        int length = JSONArray.length;

        try
        {

            for (int i=0; i<length; i++)
            {
                Route route = new Route();
                JSONObject finalObject = new JSONObject(JSONArray[i]);
                Log.i(TAG,JSONArray[i]);
                String ID = finalObject.getString("id");

                if(!ID.equals("null")){
                    route.setID(finalObject.getInt("id"));
                }
                else{
                    route.setID(0);
                }

                route.setName(finalObject.getString("name"));
                route.setColor(finalObject.getString("color"));
                route.setStatus(finalObject.getInt("status"));
                route.setInService(finalObject.getInt("inService"));
                route.setShape(finalObject.getString("shape"));
                routes.add(route);
            }
    } catch (JSONException e)
    {
        e.printStackTrace();
    }

    // return finalJason;
    return routes;
}

    @Override
    protected void onPostExecute(ArrayList<Route> r)
    {
        output = r;
    }

    private String[] getdata(String urlstr)
    {

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        try
        {
            URL url = new URL(urlstr);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            String line = "";
            while ((line = reader.readLine()) != null)
            {
                line = line.replace("[{", "{");
                line = line.replace("}];", "}");
                line = line.replace("},","}~");
                buffer.append(line);
            }
            Log.d(TAG, buffer.toString());
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
        String[] array = buffer.toString().split("~");
        return array;
    }
}


