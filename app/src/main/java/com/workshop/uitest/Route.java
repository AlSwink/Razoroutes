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
    private String  StrShape;
    private int Status;
    private int inService;
    private String  Description;
    private String  url;
    private String  NextArrival;
    private Double  Length;
    private int     DepartureStop;
    private String  NextDeparture;

    public Route(int ID, String name, String description, String color, String shape, int status, int inService, String url, String nextArrival, Double length, int departureStop, String nextDeparture)
    {
        this.ID = ID;
        Name = name;
        Description = description;
        Color = color;
        StrShape = shape;
        Status = status;
        this.inService = inService;
        this.url = url;
        NextArrival = nextArrival;
        Length = length;
        DepartureStop = departureStop;
        NextDeparture = nextDeparture;
        setShape(shape);
    }
    public Route() {}

    public int getID()          {return ID;}
    public String getName()     {return Name;}
    public String getColor()    {return Color;}
    public List<LatLng> getShape(){return Shape;}
    public int getStatus()      {return Status;}

    public void setID(int id)   {ID =id;}
    public void setName(String name){Name = name;}
    public void setColor(String color){Color = color;}
    public void setStatus(int status){Status = status;}
    // public void setInService(int service){
    //     if(service==1)
    //         inService="Yes";
    //     else
    //         inService="No";
    // }
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

    public String getDescription()
    {
        return Description;
    }

    public void setDescription(String description)
    {
        Description = description;
    }

    public void setStrShape(String sape)
    {
        StrShape = sape;
    }

    public int isInService()
    {
        return inService;
    }

    public void setInService(int inService)
    {
        this.inService = inService;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getNextArrival()
    {
        return NextArrival;
    }

    public void setNextArrival(String nextArrival)
    {
        NextArrival = nextArrival;
    }

    public Double getLength()
    {
        return Length;
    }

    public void setLength(Double length)
    {
        Length = length;
    }

    public int getDepartureStop()
    {
        return DepartureStop;
    }

    public void setDepartureStop(int departureStop)
    {
        DepartureStop = departureStop;
    }

    public String getNextDeparture()
    {
        return NextDeparture;
    }

    public void setNextDeparture(String nextDeparture)
    {
        NextDeparture = nextDeparture;
    }
}
