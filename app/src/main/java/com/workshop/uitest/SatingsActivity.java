package com.workshop.uitest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class SatingsActivity extends AppCompatActivity
{
    private Switch networkin;
    private SeekBar updatetimebar;
    private TextView updatetimetext;

    public int getUpdateTime() { return updateTime; }

    private int updateTime;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satings);
        initializeVariables();
        setliseners();

    }
    private void initializeVariables()
    {
        networkin = (Switch) findViewById(R.id.setings_network_switch);
        updatetimebar = (SeekBar) findViewById(R.id.setings_update_seekBar);
        updatetimetext = (TextView) findViewById(R.id.setings_updata_time);

        setTextForUpdateTime();
    }
    private void setTextForUpdateTime()
    {
        if (networkin.isChecked())
            updateTime = 15;
        else
            updateTime = 0;

        updatetimetext.setText(""+updateTime+" sec");

    }
    private void setliseners()
    {
       updatetimebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
       {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
           {
               updateTime = progress;
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {          }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar)
           {
               updatetimetext.setText(""+updateTime+" sec");
           }
       });

        networkin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                setTextForUpdateTime();
            }
        });
    }
}
