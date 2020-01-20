package com.dsta.AWSSQSListener;

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;


import javax.jms.*;

public class AWSSQSListener {

    public static final Region DEFAULT_REGION = Region.getRegion(Regions.AP_SOUTHEAST_1);

    public void runListener() throws JMSException,InterruptedException{


        SQSConnectionFactory connectionFactory =
                SQSConnectionFactory.builder()
                        .withRegion(DEFAULT_REGION)
                        .withAWSCredentialsProvider(new DefaultAWSCredentialsProviderChain())
                        .build();

        SQSConnection connection = connectionFactory.createConnection();

        AmazonSQSMessagingClientWrapper client = connection.getWrappedAmazonSQSClient();

        if(!client.queueExists("MyAsyncDemoQueue")){
            client.createQueue("MyAsyncDemoQueue");
        }

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("MyAsyncDemoQueue");

        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MyQueueListener());


        connection.start();
        Thread.sleep(1000);
    }
}
