package com.dsta.gateway.connection;

/**
 * Created by dkith on 21/11/2019.
 */
import java.util.List;
import java.util.Map.Entry;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

public class AWSOS3Connection extends BaseConnection{



    @Override
    public void run() {

        try {
            credentialsProvider.getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                            "Please make sure that your credentials file is at the correct " +
                            "location (~/.aws/credentials), and is in valid format.",
                    e);
        }

        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(Regions.AP_SOUTHEAST_1 )
                .build();

        while(true){

            try {

                // Receive messages
                GetQueueUrlResult result = sqs.getQueueUrl(queueName);

                System.out.println("Receiving messages from MyQueue.\n");

                ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(result.getQueueUrl());
                List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();

                for (Message message : messages) {

                    if(!messageIDs.contains(message.getMessageId())){
                        System.out.println("  Message");
                        System.out.println("    MessageId:     " + message.getMessageId());
                        System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
                        System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
                        System.out.println("    Body:          " + message.getBody());
                        for (Entry<String, String> entry : message.getAttributes().entrySet()) {
                            System.out.println("  Attribute");
                            System.out.println("    Name:  " + entry.getKey());
                            System.out.println("    Value: " + entry.getValue());
                        }

                        messageIDs.add(message.getMessageId());
                        proceedToBackend(message);
                    }

                }

                // Delete a message
                //System.out.println("Deleting a message.\n");
                //String messageReceiptHandle = messages.get(0).getReceiptHandle();
                //sqs.deleteMessage(new DeleteMessageRequest(myQueueUrl, messageReceiptHandle));
            } catch (AmazonServiceException ase) {
                System.out.println("Caught an AmazonServiceException, which means your request made it " +
                        "to Amazon SQS, but was rejected with an error response for some reason.");
                System.out.println("Error Message:    " + ase.getMessage());
                System.out.println("HTTP Status Code: " + ase.getStatusCode());
                System.out.println("AWS Error Code:   " + ase.getErrorCode());
                System.out.println("Error Type:       " + ase.getErrorType());
                System.out.println("Request ID:       " + ase.getRequestId());
            } catch (AmazonClientException ace) {
                System.out.println("Caught an AmazonClientException, which means the client encountered " +
                        "a serious internal problem while trying to communicate with SQS, such as not " +
                        "being able to access the network.");
                System.out.println("Error Message: " + ace.getMessage());
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void proceedToBackend(Message message){

    }

}
