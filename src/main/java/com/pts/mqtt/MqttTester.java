package com.pts.mqtt;

import java.util.Random;

public class MqttTester {
    public static void main(String[] args) throws InterruptedException {

        SensorEngine sensorEngine = new SensorEngine("pts.batman.edu.tr", 38880,"admin","admin","rtu/1/temperature/1");
        new Thread(sensorEngine).start();
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            Thread.sleep(400);
            double value = random.nextDouble() * 60 + random.nextDouble() * 60 + random.nextDouble() * 60
                    + random.nextDouble() * 60+ random.nextDouble() * 60+ random.nextDouble() * 60;

            sensorEngine.send(new SensorData(1,value));
        }
        sensorEngine.close();
    }

}
