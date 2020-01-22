package com.dsta.springbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootApp {
    public static void main(String[] args) {

        SpringApplication.run(SpringBootApp.class, args);

        try {
            //AWSQSPublisherBase.runPublisher();
            //AWSQSPublisher publisher = new AWSQSPublisher();
            //publisher.runPublisher();

            //AWSSQSListener listener = new AWSSQSListener();
            //listener.runListener();

            //MainAppQueueListener listener = new MainAppQueueListener();
            //listener.createListener();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
