package com.pts.mqtt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.paho.client.mqttv3.*;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class SensorEngine implements Runnable {
    String hostIp;
    int port;
    String username;
    String password;
    String publisherId = UUID.randomUUID().toString();
    IMqttClient publisher;
    String TOPIC;
    Gson gson;

    public SensorEngine(String hostIp, int port, String username, String password, String TOPIC) {
        this.hostIp = hostIp;
        this.port = port;
        this.username = username;
        this.password = password;
        this.TOPIC = TOPIC;
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

            publisher.subscribe(TOPIC, (topic, msg) -> {
                byte[] payload = msg.getPayload();
                System.out.println(new String(payload));
            });
        }catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
    }
    public boolean send(Object data)
    {
        try {
            publisher.publish(TOPIC, new MqttMessage(gson.toJson(data).getBytes(Charset.forName("UTF-8"))));
        } catch (MqttException e) {
            return false;
        }
        return true;
    }

}
