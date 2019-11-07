package com.pts.mqtt;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MqttTester {
    public static void main(String[] args) throws InterruptedException {
        SensorEngine sensorEngine = new SensorEngine("localhost", 38880,"test1","test1","sensor/1");


        ExecutorService service = Executors.newFixedThreadPool(1);
        service.submit(sensorEngine);

         Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Thread.sleep(500);
            sensorEngine.send(random.nextDouble() * 60 + random.nextDouble() * 60 + random.nextDouble() * 60
                    + random.nextDouble() * 60+ random.nextDouble() * 60+ random.nextDouble() * 60);
        }
        service.shutdown();

    }
}
