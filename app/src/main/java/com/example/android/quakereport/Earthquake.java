package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {

    private String mag, location, date, url, time;

    public Earthquake(String magnitude, String location, String date, String url){
        this.date = date;
        this.location = location;
        this.mag = magnitude;
        this.url = url;
    }

    public String getMagnitude(){
        return mag;
    }
    public String getLocation(){
        return location;
    }
    public String getDate(){
        long time = Long.parseLong(date);
        Date dateToFormat = new Date(time);

        String dateFull [] = dateToFormat.toString().split(" ");
        String dateToDisplay = dateFull[1] +" " +dateFull[2]+", "+dateFull[5];

        return dateToDisplay;
    }
    public String getTime(){

        long time = Long.parseLong(date);
        Date dateToFormat = new Date(time);

        String dateFull [] = dateToFormat.toString().split(" ");

        String timeFull [] = dateFull[3].split(":");

        int minutes = Integer.parseInt(timeFull[1]);
        int hour = Integer.parseInt(timeFull[0]);

        int carry = 0;

        if(minutes < 30){
            minutes = 60 + minutes - 30;
            carry = 1;
        }else{
            minutes = minutes - 30;
        }

        if(hour < 5){
            hour = 24 + hour - 5;
        }else hour = hour - 5;

        if(carry == 1) hour--;

        String h = String.valueOf(hour);
        String m = String.valueOf(minutes);

        if(hour < 10){
            h = "0"+ h;
        }

        if(minutes < 10){
            m = "0"+ m;
        }

        String dateToDisplay = h+":"+m+":"+String.valueOf(timeFull[2])+" UTC";

        return dateToDisplay;
    }


    public String getUrl() {
        return url;
    }

}
