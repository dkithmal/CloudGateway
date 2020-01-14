package com.dsta.gateway;

/**
 * Created by dkith on 21/11/2019.
 */
import java.util.ArrayList;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.dsta.gateway.connection.AWSOS3Connection;


public class OS3Gateway extends BaseGateway{

    @Override
    public void init() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAR57FVOHNPAB7BE5O", "l2b/sKXJ/jNiigBqT22Vvp4tO011/NkxLYTGCL/d");
        credentialsProvider = new AWSStaticCredentialsProvider(awsCreds);
        //credentialsProvider = new ProfileCredentialsProvider();
        queueNameList = new ArrayList<String>();
        queueNameList.add("poc-os3toprem");
    }

    @Override
    public void connect() {
        for(String queueName : queueNameList){
            AWSOS3Connection connection = new AWSOS3Connection();
            connection.setCredentialsProvider(credentialsProvider);
            connection.setQueueName(queueName);
            connection.start();
        }

    }

}
