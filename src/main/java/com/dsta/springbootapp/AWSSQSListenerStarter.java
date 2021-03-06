package com.dsta.springbootapp;

import com.dsta.service.awssqslistener.AWSSQSListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AWSSQSListenerStarter {
    public static void main(String[] args) {

        SpringApplication.run(AWSSQSListenerStarter.class, args);

        try {

            AWSSQSListener listener = new AWSSQSListener();
            listener.startListen();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
