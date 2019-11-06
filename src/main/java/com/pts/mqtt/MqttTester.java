package com.pts.mqtt;

import java.util.Random;

public class MqttTester {
    public static void main(String[] args) throws InterruptedException {
        SensorEngine sensorEngine = new SensorEngine("localhost", 1883,"test1","test1","sensor/1");
        new Thread(sensorEngine).start();
        Random random = new Random();


        for (int i = 0; i < 100; i++) {
            Thread.sleep(500);
            sensorEngine.send(random.nextDouble() * 60 + random.nextDouble() * 60 + random.nextDouble() * 60
                    + random.nextDouble() * 60+ random.nextDouble() * 60+ random.nextDouble() * 60);
        }
    }
}
