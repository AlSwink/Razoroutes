package com.example.vitaly.razeroutes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity
{
    TextView theoutput;
    private final static String TAG = "TESTING";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Just testing
         */
        theoutput = (TextView) findViewById(R.id.output);
        Button btn = (Button) findViewById(R.id.btnHit);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // new JSONTask(theoutput).execute("http://jsonparsing.parseapp.com/jsonData/moviesDemoItem.txt");
                // new JSONTask(theoutput).execute("http://jsonparsing.parseapp.com/jsonData/moviesDemoList.txt");
                // new JSONTask(theoutput).execute("https://campusdata.uark.edu/api/routes");
                // new JSONTask(theoutput).execute("https://campusdata.uark.edu/api/stops?routeIds");
                // new JSONTask(theoutput).execute("https://campusdata.uark.edu/api/buses?callback=?&routeIds");
                new JSONTask(theoutput).execute("https://campusdata.uark.edu/api/buses?callback=?&routeIds=23");
            }
        });
    }
}
