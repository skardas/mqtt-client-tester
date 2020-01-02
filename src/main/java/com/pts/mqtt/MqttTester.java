package com.pts.mqtt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MqttTester {
    public static void main(String[] args) throws InterruptedException {

        String [][] topics = new String[10][12];
        for (int i = 0; i < topics.length; i++) {
            for (int j = 0; j < topics[0].length; j++) {
                topics[i][j] = "rtu/pn-"+(i+1)+"/sensor/"+ (j+1);
            }
        }
        ExecutorService executorService = Executors.newFixedThreadPool(topics.length);
        SensorEngine [] sensorEngine = new SensorEngine[topics.length];
        for (int i = 0; i < sensorEngine.length; i++) {
            sensorEngine[i] = new SensorEngine("pts.batman.edu.tr", 38880,"credential"+(i+1),"credential"+(i+1),topics[i]);
            executorService.submit(sensorEngine[i]);
        }

        executorService.awaitTermination(100, TimeUnit.SECONDS);

        for (SensorEngine en:sensorEngine){
            en.close();
        }

     }

    public enum SensorType {
        Temperature, Flow, Vibration, Tilt, Torque, Pressure, Level, Ultrasonic, Light, Infrared, Accelerometer, Gyroscope;

        public static SensorType toSensorType(String name){
            for(SensorType type: values())
            {
                if(type.name().toLowerCase().startsWith(name))
                    return type;
            }
            return SensorType.Temperature;
        }
    }
}
