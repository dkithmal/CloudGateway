package com.dsta.DeadLetterQueuePublisher;

import com.dsta.MainAppQueuePublisher.MainQueuePublisherBase;

import javax.jms.JMSException;

public class DeadLetterQueuePublisher {
    public static DeadLetterQueuePublisherBase deadLetterQueuePublisherBase;

    private DeadLetterQueuePublisher(){}

    private static void initPublisher(){
        if(deadLetterQueuePublisherBase == null){
            deadLetterQueuePublisherBase = new DeadLetterQueuePublisherBase();
            deadLetterQueuePublisherBase.runPublisher();
        }
    }

    public static void publishMessage(String message){

        initPublisher();

        try {
            deadLetterQueuePublisherBase.publishMessage(message);
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
