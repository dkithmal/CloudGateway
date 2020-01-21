package com.dsta.DeadLetterQueuePublisher;

import com.dsta.MainAppQueuePublisher.MainQueuePublisherBase;

public class DeadLetterQueuePublisher {
    public static DeadLetterQueuePublisherBase deadLetterQueuePublisherBase;

    private DeadLetterQueuePublisher(){}

    public static DeadLetterQueuePublisherBase getPublisher(){
        if(deadLetterQueuePublisherBase == null){
            deadLetterQueuePublisherBase = new DeadLetterQueuePublisherBase();
            deadLetterQueuePublisherBase.runPublisher();
        }

        return deadLetterQueuePublisherBase;
    }
}
