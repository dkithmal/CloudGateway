package com.dsta.AWSSQSPublisher;

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

        try {
            initPublisher();
        } catch (Exception e) {
            publisher = null;
            awssqsMsgCompletionListener.onException(message,e);
        }

        if(publisher != null){
            try {
                publisher.publishMessage(message);
            } catch (JMSException e) {
                awssqsMsgCompletionListener.onException(message,e);
            }
        }

    }
}
