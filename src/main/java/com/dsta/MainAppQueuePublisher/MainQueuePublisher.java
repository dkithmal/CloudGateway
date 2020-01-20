package com.dsta.MainAppQueuePublisher;

public class MainQueuePublisher {

    public static MainQueuePublisherBase mainQueuePublisherBase;

    private MainQueuePublisher(){}

    public static MainQueuePublisherBase getPublisher(){
        if(mainQueuePublisherBase == null){
            mainQueuePublisherBase = new MainQueuePublisherBase();
            mainQueuePublisherBase.runPublisher();
        }

        return mainQueuePublisherBase;
    }
}
