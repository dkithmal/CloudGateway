package com.dsta.service.mainappqueuepublisher;

import javax.jms.CompletionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class MainQueueMsgCompletionListener implements CompletionListener {
    @Override
    public void onCompletion(Message message) {
        System.out.println("Message Sent Successfully to The Main Queue ");

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
        System.out.println("Message: " + msgText);
/*        try {
            System.out.println(message.getJMSMessageID());
        } catch (JMSException e) {
            e.printStackTrace();
        }*/
    }
    @Override
    public void onException(Message message, Exception e) {
        System.out.println("onException while sending message to the Main Queue");

        String msgText = "";
        if (message instanceof TextMessage) {
            try {
                msgText = ((TextMessage)message).getText();
            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        } else {
            msgText = message.toString();
        }
        System.out.println("Message: " + msgText);
/*        try {
            System.out.println(message.getJMSMessageID());
        } catch (JMSException ex) {
            ex.printStackTrace();
        }*/

    }
}
