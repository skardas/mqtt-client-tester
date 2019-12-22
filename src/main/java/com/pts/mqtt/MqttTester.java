package com.pts.mqtt;

import java.util.Arrays;
import java.util.Random;

public class MqttTester {
    public static void main(String[] args) throws InterruptedException {

        String topic = "rtu/10/Torque/1001";
        SensorEngine sensorEngine = new SensorEngine("pts.batman.edu.tr", 38880,"admin","admin",topic);
        new Thread(sensorEngine).start();
        Random random = new Random();
        Thread.sleep(1000);

        for (int i = 0; i < 10000; i++) {
            Thread.sleep(50);
            double value = random.nextDouble() * 60 + random.nextDouble() * 60 + random.nextDouble() * 60
                    + random.nextDouble() * 60+ random.nextDouble() * 60+ random.nextDouble() * 60;
            for (int j = 1; j <= 10; j++) {
                sensorEngine.send(topic,new SensorData(value));
            }
        }
        sensorEngine.close();
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
