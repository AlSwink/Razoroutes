package com.workshop.uitest;

import android.content.Context;
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

/**
 * Created by borodin on 4/15/2016.
 */
class JSONTask extends AsyncTask<String , String, String>
{
    TextView output = null;

    JSONTask (TextView tv)
    {
        output = tv;
    }
    private final static String TAG = "TESTING";
    @Override
    protected String doInBackground(String ... params)
    {
        Buse buse = new Buse();
        StringBuffer retValBuffer = new StringBuffer();
        String finalJson = getdata(params[0]);
        Log.i(TAG,finalJson);
        try
        {
                /*
                This is cove staff for the array but so far I use only singel objekt.
                 */
            // JSONObject parentObject = new JSONObject(finalJason);
            // JSONArray parentArray = parentObject.getJSONArray("");


                //JSONObject finalObject = parentArray.getJSONObject(i);
                JSONObject finalObject = new JSONObject(finalJson);

                String ID = finalObject.getString("id");
                String fleet = finalObject.getString("fleet");
                String zonar = finalObject.getString("zonarId");

                if(!ID.equals("null")){
                    buse.setId(finalObject.getInt("id"));
                }
                else{
                    buse.setId(0);
                }
                if(!fleet.equals("null")){
                    buse.setFleer(finalObject.getInt("fleet"));
                }
                else{
                    buse.setFleer(0);
                }

                buse.setName(finalObject.getString("name"));
                buse.setDescription(finalObject.getString("description"));
                if(!zonar.equals("null")){
                    buse.setZonarId(finalObject.getInt("zonarId"));
                }
                else{
                    buse.setZonarId(0);
                }
                buse.setGpsId(finalObject.getString("gpsId"));
                buse.setLatitude(finalObject.getDouble("latitude"));
                buse.setLongitude(finalObject.getDouble("longitude"));
                buse.setSpeed(finalObject.getDouble("speed"));
                buse.setColor(finalObject.getString("color"));

                String retval =  "ID = " + buse.getId() + "\n" +
                        "Fleet = " + buse.getFleer() + "\n" +
                        "Name = " + buse.getName() + "\n" +
                        "Description = " + buse.getDescription() + "\n" +
                        "ZonarID = " + buse.getZonarId() + "\n" +
                        "GPS ID = " + buse.getGpsId() + "\n" +
                        "Latitude = " + buse.getLatitude() + "\n" +
                        "Longitude = " + buse.getLongitude() + "\n" +
                        "Speed = " + buse.getSpeed() + "\n" +
                        "Color = " + buse.getColor();

                retValBuffer.append(retval);


        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        // return finalJason;
        return retValBuffer.toString();
    }

    @Override
    protected void onPostExecute(String s)
    {
        output.setText(s);
    }

    private String getdata(String urlstr)
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
                line = line.replace("?([{", "{");
                line = line.replace("}]);", "}");


                buffer.append(line);
            }
            //Log.d(TAG, buffer.toString());
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
