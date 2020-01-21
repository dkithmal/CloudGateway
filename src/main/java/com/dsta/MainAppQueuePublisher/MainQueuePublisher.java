package com.dsta.MainAppQueuePublisher;

import javax.jms.JMSException;

public class MainQueuePublisher {

    public static MainQueuePublisherBase mainQueuePublisherBase;

    private MainQueuePublisher(){}

    private static void initPublisher(){
        if(mainQueuePublisherBase == null){
            mainQueuePublisherBase = new MainQueuePublisherBase();
            mainQueuePublisherBase.runPublisher();
        }
    }

    public static void publishMessage(String message){

        initPublisher();

        try {
            mainQueuePublisherBase.publishMessage(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
