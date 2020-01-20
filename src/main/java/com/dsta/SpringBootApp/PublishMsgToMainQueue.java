package com.dsta.SpringBootApp;


import com.dsta.MainAppQueuePublisher.MainQueuePublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PublishMsgToMainQueue {

    public static void main(String[] args) {

        SpringApplication.run(PublishMsgToMainQueue.class, args);

        try {
            MainQueuePublisher publisher = new MainQueuePublisher();
            publisher.publishMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
