package com.example.vitaly.razeroutes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

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
