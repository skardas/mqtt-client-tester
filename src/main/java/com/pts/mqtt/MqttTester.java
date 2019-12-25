package com.pts.mqtt;

import java.util.Arrays;
import java.util.Random;

public class MqttTester {
    public static void main(String[] args) throws InterruptedException {

        String [] topics = new String[]{"rtu/rtu-378/sensor/sensor-1",
                "rtu/rtu-975/sensor/sensor-2",
                "rtu/rtu-984/sensor/sensor-3",
                "rtu/rtu-982/sensor/sensor-4"};
        SensorEngine sensorEngine = new SensorEngine("localhost", 38880,"admin","admin",topics[0]);
        new Thread(sensorEngine).start();
        Random random = new Random();
        Thread.sleep(1000);

        for (int i = 0; i < 10000; i++) {
             for (String topic: topics)
             {
                 double value = random.nextDouble() * 60 + random.nextDouble() * 60 + random.nextDouble() * 60
                         + random.nextDouble() * 60+ random.nextDouble() * 60+ random.nextDouble() * 60;
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
