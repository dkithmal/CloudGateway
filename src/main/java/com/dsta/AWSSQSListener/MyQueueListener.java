package com.dsta.AWSSQSListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyQueueListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("SQS Listener Trigger");
        
        String msgText = "";
        if (message instanceof TextMessage) {
            try {
                msgText = ((TextMessage)message).getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            msgText = message.toString();
        }

        System.out.println(msgText);
    }
}
