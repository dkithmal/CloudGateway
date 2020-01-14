package com.dsta.CloudGatewayJMS;

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

import javax.jms.*;

/**
 * Created by dkith on 1/14/2020.
 */
public class SQSAsyncDemo {


    public void runASyncQueueDemo() throws JMSException,InterruptedException{

        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(new ProviderConfiguration(), AmazonSQSClientBuilder.defaultClient());

        SQSConnection connection = connectionFactory.createConnection();

        AmazonSQSMessagingClientWrapper client = connection.getWrappedAmazonSQSClient();

        if(!client.queueExists("MyAsyncDemoQueue")){
            client.createQueue("MyAsyncDemoQueue");
        }

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("MyAsyncDemoQueue");

        MessageProducer producer = session.createProducer(queue);

        TextMessage message =  session.createTextMessage("Text Message");

        producer.send(message);

        System.out.println("JMS Message ID"+ message.getJMSMessageID());

        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(new MyQueueListner());

        connection.start();

        Thread.sleep(1000);


    }
}
