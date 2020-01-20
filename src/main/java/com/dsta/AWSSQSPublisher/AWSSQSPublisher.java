package com.dsta.AWSSQSPublisher;

import javax.jms.JMSException;

public class AWSSQSPublisher {

    public static AWSQSPublisherBase publisher;

    private AWSSQSPublisher(){}

    public static AWSQSPublisherBase getPublisher(){
        if(publisher == null){
            publisher = new AWSQSPublisherBase();
            try {
                publisher.runPublisher();
            } catch (JMSException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return publisher;
    }
}
