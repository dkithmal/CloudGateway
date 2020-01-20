package com.dsta.AWSSQSPublisher;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class AWSSQSMsgCompletionListenerImpl implements AWSSQSMsgCompletionListener{

    @Override
    public void onCompletion(Message message) {
        System.out.println("Message sent successfully to the AWS SQS ");

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
    }

    @Override
    public void onException(String messageStr, Exception e) {
        System.out.println("Message couldn't sent to the AWS SQS");
        System.out.println("Message: " + messageStr);
    }
}
