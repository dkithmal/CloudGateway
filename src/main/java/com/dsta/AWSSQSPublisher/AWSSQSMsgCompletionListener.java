package com.dsta.AWSSQSPublisher;

import javax.jms.Message;

public interface AWSSQSMsgCompletionListener {
    void onCompletion(Message message);

    void onException(String messageStr, Exception var2);
}