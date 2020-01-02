package com.pts.mqtt;

  import java.util.Calendar;
import java.util.Date;
  import java.util.Random;

public class SensorData {
    double value;
    Date time;

    public SensorData(int minusTime,double value) {
        this.value = value;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,minusTime);
        time =  calendar.getTime();
       // time =  Calendar.getInstance().getTime();

    }
}
