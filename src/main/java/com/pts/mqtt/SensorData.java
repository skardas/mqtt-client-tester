package com.pts.mqtt;

 import java.util.Date;

public class SensorData {
    double value1;
    Date time;

    public SensorData(Date time,double value) {
        this.value1 = value;
       this.time = time;
       // time =  Calendar.getInstance().getTime();

    }
}
