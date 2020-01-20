package com.dsta.MainApp;

import com.dsta.AWSSQSPublisher.MainAppQueueListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootApp {
    public static void main(String[] args) {

        SpringApplication.run(SpringBootApp.class, args);

        try {
            //MainQueuePublisher.createConnection();
            MainAppQueueListener listener = new MainAppQueueListener();
            listener.createListener();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
