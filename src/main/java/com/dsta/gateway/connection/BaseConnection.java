package com.dsta.gateway.connection;

/**
 * Created by dkith on 21/11/2019.
 */
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.sqs.model.Message;

public abstract class BaseConnection extends Thread{

    public String queueName;
    public AWSCredentialsProvider credentialsProvider;
    List<String> messageIDs = new ArrayList<>();
    public abstract void proceedToBackend(Message message);



    public String getQueueName() {
        return queueName;
    }
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
    public AWSCredentialsProvider getCredentialsProvider() {
        return credentialsProvider;
    }
    public void setCredentialsProvider(AWSCredentialsProvider credentialsProvider) {
        this.credentialsProvider = credentialsProvider;
    }
    public List<String> getMessageIDs() {
        return messageIDs;
    }
    public void setMessageIDs(List<String> messageIDs) {
        this.messageIDs = messageIDs;
    }

}
