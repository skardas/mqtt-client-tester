package com.pts.mqtt;

 import java.util.Date;

public class SensorData {
    double value;
    Date time;

    public SensorData(Date time,double value) {
        this.value = value;
       this.time = time;
       // time =  Calendar.getInstance().getTime();

    }
}
