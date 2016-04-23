package com.workshop.uitest;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Alan Swink on 4/18/2016.
 */
public class Route {
    private int ID;
    private String Name;
    private String Color;
    private List<LatLng> Shape;
    private int Status;
    private String inService;

    public int getID()          {return ID;}
    public String getName()     {return Name;}
    public String getColor()    {return Color;}
    public List<LatLng> getShape(){return Shape;}
    public int getStatus()      {return Status;}
    public String getInService(){return inService;}

    public void setID(int id)   {ID =id;}
    public void setName(String name){Name = name;}
    public void setColor(String color){Color = color;}
    public void setStatus(int status){Status = status;}
    public void setInService(int service){
        if(service==1)
            inService="Yes";
        else
            inService="No";
    }
    public void setShape(String shape){
        String[] pairs = shape.split(",");
        for(int i=0;i<pairs.length;i++){
            String[]pair = pairs[i].split(" ");
            double lat = Double.parseDouble(pair[0]);
            double lng = Double.parseDouble(pair[1]);
            LatLng latLng = new LatLng(lat,lng);
            Shape.add(latLng);
        }
    }

}
