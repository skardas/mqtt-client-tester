package com.pts.mqtt;

import java.util.Random;

public class MqttTester {
    public static void main(String[] args) throws InterruptedException {
        SensorEngine sensorEngine = new SensorEngine("localhost", 38880,"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTU3Mzc2NzQ0MX0.HtFAG4ciKkSpcwxqFMiukIIQH7i7IX0rEwmllJlpK6iY9Gd5MEfWNuGsz2ch1UeV75KDsmDedUvBVT2lWUXyOw",null,"sensor/1");
        new Thread(sensorEngine).start();
        Random random = new Random();


        for (int i = 0; i < 100; i++) {
            Thread.sleep(500);
            sensorEngine.send(random.nextDouble() * 60 + random.nextDouble() * 60 + random.nextDouble() * 60
                    + random.nextDouble() * 60+ random.nextDouble() * 60+ random.nextDouble() * 60);
        }
    }

}
