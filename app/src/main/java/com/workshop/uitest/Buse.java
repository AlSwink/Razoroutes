package com.workshop.uitest;

/**
 * Created by borodin on 4/15/2016.
 */
public class Buse
{
    private int ID;
    private int Fleer;
    private String Name;
    private String Description;
    private int ZonarId;
    private String GpsId;
    private double Latitude;
    private double Longitude;
    private double Speed;
    private String Heading;
    private boolean Power;
    private String Date;
    private String Color;
    private String RouteName;
    private int RouteId;
    private double Distance;
    private String NextStop;
    private String NextArrival;


    public int getId()              { return ID;}
    public int getFleer()           { return Fleer;}
    public String getName()         { return Name;}
    public String getDescription()  { return Description;}
    public int getZonarId()         { return ZonarId;}
    public String getGpsId()        { return GpsId;}
    public double getLatitude()     { return Latitude;}
    public double getLongitude()    { return Longitude;}
    public double getSpeed()        { return Speed;}
    public String getHeading()      { return Heading;}
    public boolean getPower()       { return Power;}
    public String getDate()         { return Date;}
    public String getColor()        { return Color;}
    public String getRouteName()    { return RouteName;}
    public int getRouteId()         { return RouteId;}
    public double getDistance()     { return Distance;}
    public String getNextStop()     { return NextStop;}
    public String getNextArrival()  { return NextArrival;}

    public void setId(int id)                       { ID = id;}
    public void setFleer(int fleer)                 { Fleer = fleer;}
    public void setName(String name)                { Name = name;}
    public void setDescription(String description)  { Description = description;}
    public void setZonarId(int zonarId)             { ZonarId = zonarId;}
    public void setGpsId(String gpsId)              { GpsId = gpsId;}
    public void setLatitude(double latitude)        { Latitude = latitude;}
    public void setLongitude(double longitude)      { Longitude = longitude;}
    public void setSpeed(double speed)              { Speed = speed;}
    public void setHeading(String heading)          { Heading = heading;}
    public void setPower(boolean power)             { Power = power;}
    public void setDate(String date)                { Date = date;}
    public void setColor(String color)              { Color = color;}
    public void setRouteName(String routeName)      { RouteName = routeName;}
    public void setRouteId(int routeId)             { RouteId = routeId;}
    public void setDistance(double distance)        { Distance = distance;}
    public void setNextStop(String nextStop)        { NextStop = nextStop;}
    public void setNextArrival(String nextArrival)  { NextArrival = nextArrival;}

}