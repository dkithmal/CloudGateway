package com.dsta.service.awssqspublisher;

import com.dsta.util.Util;

import javax.jms.JMSException;

public class AWSSQSPublisher {

    public static AWSQSPublisherBase publisher;

    private AWSSQSPublisher(){}

    private static void initPublisher() throws Exception {
        if(publisher == null){
            publisher = new AWSQSPublisherBase();
            publisher.runPublisher();
        }
    }

    public static void publishMessage(String message,AWSSQSMsgCompletionListenerImpl awssqsMsgCompletionListener){
        String originalMessage = Util.getOriginalMessage(message);

        try {
            initPublisher();
        } catch (Exception e) {
            publisher = null;
            awssqsMsgCompletionListener.onException(message,e);
        }

        if(publisher != null){
            try {
                publisher.publishMessage(originalMessage);
            } catch (JMSException e) {
                awssqsMsgCompletionListener.onException(message,e);
            }
        }
        awssqsMsgCompletionListener.onCompletion(originalMessage);
    }
}
