package com.dsta.gateway;

/**
 * Created by dkith on 21/11/2019.
 */
import java.util.List;

import com.amazonaws.auth.AWSCredentialsProvider;


public abstract class BaseGateway {

    public AWSCredentialsProvider credentialsProvider;
    public List<String> queueNameList;
    public abstract void init();
    public abstract void connect();
}
