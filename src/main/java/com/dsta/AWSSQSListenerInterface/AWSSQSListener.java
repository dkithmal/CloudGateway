package com.dsta.AWSSQSListenerInterface;

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.dsta.CloudGatewayJMS.MyQueueListner;

import javax.jms.*;

public class AWSSQSListener {

    public static final Region DEFAULT_REGION = Region.getRegion(Regions.AP_SOUTHEAST_1);

    public void runListener() throws JMSException,InterruptedException{


        SQSConnectionFactory connectionFactory =
                SQSConnectionFactory.builder()
                        .withRegion(DEFAULT_REGION)
                        .withAWSCredentialsProvider(new DefaultAWSCredentialsProviderChain())
                        .build();

        //SQSConnectionFactory connectionFactory = new SQSConnectionFactory(new ProviderConfiguration(),
        //  AmazonSQSClientBuilder.defaultClient());

        SQSConnection connection = connectionFactory.createConnection();

        AmazonSQSMessagingClientWrapper client = connection.getWrappedAmazonSQSClient();

        if(!client.queueExists("MyAsyncDemoQueue")){
            client.createQueue("MyAsyncDemoQueue");
        }

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("MyAsyncDemoQueue");

        //MessageProducer producer = session.createProducer(queue);

        //TextMessage message =  session.createTextMessage("Text Message");

        //producer.send(message);

        //System.out.println("JMS Message ID"+ message.getJMSMessageID());

        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(new MyQueueListner());

        connection.start();

        Thread.sleep(1000);


    }
}
