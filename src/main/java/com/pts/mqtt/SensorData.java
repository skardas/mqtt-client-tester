package com.pts.mqtt;

import java.util.Calendar;
import java.util.Date;

public class SensorData {
    int sensorId;
    double value;
    Date time = Calendar.getInstance().getTime();
    public SensorData(int sensorId, double value) {
        this.sensorId = sensorId;
        this.value = value;
    }
}
