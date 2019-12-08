package com.pts.mqtt;

import java.util.Arrays;
import java.util.Random;

public class MqttTester {
    public static void main(String[] args) throws InterruptedException {

        SensorEngine sensorEngine = new SensorEngine("localhost", 38880,"admin","admin","rtu/1/Temperature/1");
        new Thread(sensorEngine).start();
        Random random = new Random();
        Thread.sleep(1000);

        for (int i = 0; i < 10000; i++) {
            Thread.sleep(100);
            double value = random.nextDouble() * 60 + random.nextDouble() * 60 + random.nextDouble() * 60
                    + random.nextDouble() * 60+ random.nextDouble() * 60+ random.nextDouble() * 60;
            for (int j = 1; j <= 10; j++) {
                sensorEngine.send("rtu/1/Temperature/"+j,new SensorData(1,value));
            }
        }
        sensorEngine.close();
    }


}
