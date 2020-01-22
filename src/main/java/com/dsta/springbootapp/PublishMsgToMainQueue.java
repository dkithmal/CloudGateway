package com.dsta.springbootapp;


import com.dsta.service.mainappqueuepublisher.MainQueuePublisher;
import com.dsta.util.Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PublishMsgToMainQueue {

    public static void main(String[] args) {

        SpringApplication.run(PublishMsgToMainQueue.class, args);

        try {
            String messageStr;
            int random = (int)(Math.random() * 50 + 1);
            messageStr = "Testing Message Id:" + random;

            String messageString = Util.getWrappedMessageString(messageStr);
            MainQueuePublisher.publishMessage(messageString);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
