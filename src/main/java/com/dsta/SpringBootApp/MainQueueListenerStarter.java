package com.dsta.SpringBootApp;

import com.dsta.AWSSQSPublisher.MainAppQueueListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainQueueListenerStarter {

    public static void main(String[] args) {

        SpringApplication.run(MainQueueListenerStarter.class, args);

        try{
            MainAppQueueListener listener = new MainAppQueueListener();
            listener.createListener();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
