package com.dsta.SpringBootApp;


import com.dsta.MainAppQueuePublisher.MainQueuePublisher;
import com.dsta.MainAppQueuePublisher.MainQueuePublisherBase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PublishMsgToMainQueue {

    public static void main(String[] args) {

        SpringApplication.run(PublishMsgToMainQueue.class, args);

        try {
            MainQueuePublisher.getPublisher().publishMessage("");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
