package com.dsta.CloudGatewayJMS;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by dkith on 1/14/2020.
 */
public class MyQueueListner implements MessageListener {
    @Override
    public void onMessage(Message message) {

        try {
            System.out.println("This is new message recived from the AWS");
            System.out.println(((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
