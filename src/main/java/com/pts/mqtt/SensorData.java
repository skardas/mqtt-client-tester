package com.pts.mqtt;

  import java.util.Calendar;
import java.util.Date;
  import java.util.Random;

public class SensorData {
    double value;
    Date time;
    public SensorData(double value) {
        this.value = value;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,new Random().nextInt(3600*24*30) * -1);
        time =  calendar.getTime();
    }
}
