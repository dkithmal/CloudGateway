package com.dsta.MainApp;

import com.dsta.CloudGateway.CloudGatewayApplication;
import com.dsta.CloudGatewayJMS.SQSAsyncDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.JMSException;


@SpringBootApplication
public class SpringBootApp {
    public static void main(String[] args) {

        SpringApplication.run(CloudGatewayApplication.class, args);

        //OS3Gateway gateway = new OS3Gateway();
        //gateway.init();
        //gateway.connect();

        SQSAsyncDemo sqsAsyncDemo = new SQSAsyncDemo();
        try {
            sqsAsyncDemo.runASyncQueueDemo();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
