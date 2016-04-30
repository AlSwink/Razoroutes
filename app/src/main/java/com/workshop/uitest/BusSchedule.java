package com.workshop.uitest;

import java.util.ArrayList;

/**
 * Created by ArtiomLK on 4/26/2016.
 */
public class BusSchedule {

    private ArrayList<String> blueWeekdaySchedule;
    private ArrayList<String> blueSaturdaySchedule;

    private ArrayList<String> redWeekdaySchedule;
    private ArrayList<String> redSaturdaySchedule;

    public BusSchedule() {
        //BLUE
        //WEEKDAY
        blueWeekdaySchedule = new ArrayList<>();

        blueWeekdaySchedule.add("7:03");
        blueWeekdaySchedule.add("7:15");
        blueWeekdaySchedule.add("7:27");
        blueWeekdaySchedule.add("7:39");
        blueWeekdaySchedule.add("7:51");
        blueWeekdaySchedule.add("8:04");
        blueWeekdaySchedule.add("8:17");
        blueWeekdaySchedule.add("8:28");
        blueWeekdaySchedule.add("8:41");
        blueWeekdaySchedule.add("9:00");
        blueWeekdaySchedule.add("9:12");
        blueWeekdaySchedule.add("9:24");
        blueWeekdaySchedule.add("9:36");
        blueWeekdaySchedule.add("9:48");
        blueWeekdaySchedule.add("10:00");
        blueWeekdaySchedule.add("10:12");
        blueWeekdaySchedule.add("10:24");
        blueWeekdaySchedule.add("10:36");
        blueWeekdaySchedule.add("10:48");
        blueWeekdaySchedule.add("11:00");
        blueWeekdaySchedule.add("11:12");
        blueWeekdaySchedule.add("11:24");
        blueWeekdaySchedule.add("11:36");
        blueWeekdaySchedule.add("11:48");
        blueWeekdaySchedule.add("12:02");
        blueWeekdaySchedule.add("12:14");
        blueWeekdaySchedule.add("12:28");
        blueWeekdaySchedule.add("12:40");
        blueWeekdaySchedule.add("12:54");
        blueWeekdaySchedule.add("13:12");
        blueWeekdaySchedule.add("13:26");
        blueWeekdaySchedule.add("13:38");
        blueWeekdaySchedule.add("13:52");
        blueWeekdaySchedule.add("14:04");
        blueWeekdaySchedule.add("14:18");
        blueWeekdaySchedule.add("14:30");
        blueWeekdaySchedule.add("14:46");
        blueWeekdaySchedule.add("14:56");
        blueWeekdaySchedule.add("15:11");
        blueWeekdaySchedule.add("15:22");
        blueWeekdaySchedule.add("15:35");
        blueWeekdaySchedule.add("15:46");
        blueWeekdaySchedule.add("15:59");
        blueWeekdaySchedule.add("16:10");
        blueWeekdaySchedule.add("16:23");
        blueWeekdaySchedule.add("16:34");
        blueWeekdaySchedule.add("16:48");
        blueWeekdaySchedule.add("16:58");
        blueWeekdaySchedule.add("17:22");
        blueWeekdaySchedule.add("17:46");

        //DURING NIGHT
        blueWeekdaySchedule.add("18:00");
        blueWeekdaySchedule.add("18:30");
        blueWeekdaySchedule.add("19:00");
        blueWeekdaySchedule.add("19:30");
        blueWeekdaySchedule.add("20:10");
        blueWeekdaySchedule.add("20:40");
        blueWeekdaySchedule.add("21:10");
        blueWeekdaySchedule.add("21:40");
        blueWeekdaySchedule.add("22:10");

        //SATURDAY
        blueSaturdaySchedule = new ArrayList<>();

        blueSaturdaySchedule.add("7:00");
        blueSaturdaySchedule.add("7:30");
        blueSaturdaySchedule.add("8:00");
        blueSaturdaySchedule.add("8:30");
        blueSaturdaySchedule.add("9:00");
        blueSaturdaySchedule.add("9:30");
        blueSaturdaySchedule.add("10:00");
        blueSaturdaySchedule.add("10:30");
        blueSaturdaySchedule.add("11:00");
        blueSaturdaySchedule.add("11:30");
        blueSaturdaySchedule.add("12:00");
        blueSaturdaySchedule.add("12:30");
        blueSaturdaySchedule.add("13:00");
        blueSaturdaySchedule.add("13:30");
        blueSaturdaySchedule.add("14:00");
        blueSaturdaySchedule.add("14:30");
        blueSaturdaySchedule.add("15:00");
        blueSaturdaySchedule.add("15:30");
        blueSaturdaySchedule.add("16:00");
        blueSaturdaySchedule.add("16:30");
        blueSaturdaySchedule.add("17:00");
        blueSaturdaySchedule.add("17:30");
        //NIGHT
        blueSaturdaySchedule.add("18:00");
        blueSaturdaySchedule.add("18:30");
        blueSaturdaySchedule.add("19:00");
        blueSaturdaySchedule.add("19:30");
        blueSaturdaySchedule.add("20:10");
        blueSaturdaySchedule.add("20:40");
        blueSaturdaySchedule.add("21:10");
        blueSaturdaySchedule.add("21:40");
        blueSaturdaySchedule.add("22:10");

        //RED
        //WEEKDAY
        redWeekdaySchedule = new ArrayList<>();

        redWeekdaySchedule.add("6:54");
        redWeekdaySchedule.add("7:16");
        redWeekdaySchedule.add("7:38");
        redWeekdaySchedule.add("8:00");
        redWeekdaySchedule.add("8:22");
        redWeekdaySchedule.add("8:40");
        redWeekdaySchedule.add("9:10");
        redWeekdaySchedule.add("9:32");
        redWeekdaySchedule.add("9:58");
        redWeekdaySchedule.add("10:33");
        redWeekdaySchedule.add("11:05");
        redWeekdaySchedule.add("11:37");
        redWeekdaySchedule.add("12:09");
        redWeekdaySchedule.add("12:41");
        redWeekdaySchedule.add("13:15");
        redWeekdaySchedule.add("13:45");
        redWeekdaySchedule.add("14:33");
        redWeekdaySchedule.add("14:55");
        redWeekdaySchedule.add("15:17");
        redWeekdaySchedule.add("15:39");
        redWeekdaySchedule.add("16:01");
        redWeekdaySchedule.add("16:23");
        redWeekdaySchedule.add("16:48");
        redWeekdaySchedule.add("17:10");
        redWeekdaySchedule.add("17:30");
        //NIGHT
        redWeekdaySchedule.add("18:00");
        redWeekdaySchedule.add("19:00");
        redWeekdaySchedule.add("20:10");
        redWeekdaySchedule.add("21:10");

        //SATURDAY
        redSaturdaySchedule = new ArrayList<>();

        redSaturdaySchedule.add("7:00");
        redSaturdaySchedule.add("8:00");
        redSaturdaySchedule.add("9:00");
        redSaturdaySchedule.add("10:00");
        redSaturdaySchedule.add("11:00");
        redSaturdaySchedule.add("12:00");
        redSaturdaySchedule.add("13:00");
        redSaturdaySchedule.add("14:00");
        redSaturdaySchedule.add("15:00");
        redSaturdaySchedule.add("16:00");
        redSaturdaySchedule.add("17:00");
        //NIGHT
        redSaturdaySchedule.add("18:00");
        redSaturdaySchedule.add("19:00");
        redSaturdaySchedule.add("20:10");
        redSaturdaySchedule.add("21:10");
    }

    public ArrayList<String> getBlueWeekdaySchedule(){return blueWeekdaySchedule;}
    public ArrayList<String> getBlueSaturdaySchedule(){return blueSaturdaySchedule;}
    public ArrayList<String> getRedWeekdaySchedule(){return redWeekdaySchedule;}
    public ArrayList<String> getRedSaturdaySchedule(){return redSaturdaySchedule;}
}