package com.dsta.service.awssqspublisher;

public interface AWSSQSMsgCompletionListener {
    void onCompletion(String message);
    void onException(String message, Exception ex);
}
