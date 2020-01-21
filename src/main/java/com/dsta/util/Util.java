package com.dsta.util;

import com.dsta.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

    public static String getWrappedMessageString(String str){

        Message message = new Message();
        message.setMessage(str);
        message.setNoOfAttempts(0);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    public static String getOriginalMessage(String str){
        Message message = null;
        try {
            message = new ObjectMapper().readValue(str, Message.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return message.getMessage();
    }

    public static int getFailedAttemptsCount(String str){
        Message message = null;
        try {
            message = new ObjectMapper().readValue(str, Message.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return message.getNoOfAttempts();
    }

    public static String incrementNoOfFailedAttempts(String str){
        Message message = null;
        try {
            message = new ObjectMapper().readValue(str, Message.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        message.setNoOfAttempts(message.getNoOfAttempts()+1);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
