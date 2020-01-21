package com.dsta.AWSSQSPublisher;

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.dsta.util.Util;

import javax.jms.*;

public class AWSQSPublisherBase {

    public static final Region DEFAULT_REGION = Region.getRegion(Regions.AP_SOUTHEAST_1);

    private MessageProducer producer;
    private Session session;

    public void runPublisher() throws Exception{

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

        //Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("MyAsyncDemoQueue");

        //MessageProducer producer = session.createProducer(queue);
        producer = session.createProducer(queue);

        //TextMessage message =  session.createTextMessage("Text Message DSTA");
        //producer.send(message);
        //System.out.println("JMS Message ID"+ message.getJMSMessageID());

        connection.start();
        Thread.sleep(1000);
    }

    public void publishMessage(String messageStr) throws JMSException {
        TextMessage message = null;
        message = session.createTextMessage(messageStr);
        producer.send(message);
    }

}
