package com.pts.mqtt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.paho.client.mqttv3.*;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class SensorEngine implements Runnable {
    String hostIp;
    int port;
    String username;
    String password;
    String publisherId = UUID.randomUUID().toString();
    IMqttClient publisher;
    String [] topics;
    Gson gson;
    Random random = new Random();
    boolean isRun = true;
    public SensorEngine(String hostIp, int port, String username, String password, String [] topics) {
        this.hostIp = hostIp;
        this.port = port;
        this.username = username;
        this.password = password;
        this.topics = topics;
        gson = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();
    }

    public void run() {
        try
        {
            publisher = new MqttClient(String.format("tcp://%s:%d",hostIp,port),publisherId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            options.setUserName(username);
            if(password != null)
            options.setPassword(password.toCharArray());
            publisher.connect(options);

            publisher.subscribe(topics[0], (topic, msg) -> {
                byte[] payload = msg.getPayload();
                System.out.println("Topic: "+topics[0] +": received data :"+new String(payload));
            });
        }catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
        int minusTime = 3*30*24*60*60;
        while(isRun && minusTime > 0)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int sensorNo = 0; sensorNo < topics.length; sensorNo++) {
                double value = random.nextDouble() * 60 + random.nextDouble() * 60 + random.nextDouble() * 60
                        + random.nextDouble() * 60+ random.nextDouble() * 60+ random.nextDouble() * 60;
                send(topics[sensorNo],new SensorData(minusTime--,value));
            }
        }

    }
    public boolean send(String TOPIC,Object data)
    {
        try {

            if(publisher.isConnected())
                 publisher.publish(TOPIC, new MqttMessage(gson.toJson(data).getBytes(Charset.forName("UTF-8"))));
        } catch (MqttException e) {
            isRun = false;
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void close() {
        try {
            isRun = false;
            publisher.unsubscribe(topics[0]);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        try {
            publisher.disconnect(150);
            publisher.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
