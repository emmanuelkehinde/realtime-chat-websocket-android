package com.emmanuelkehinde.ssetest.models;

/**
 * Created by emmanuel.kehinde on 23/10/2017.
 */

public class Chat {

    private String msg;
    private String sender;

    public Chat(String msg, String sender) {
        this.msg = msg;
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
