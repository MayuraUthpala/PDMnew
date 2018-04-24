package com.example.mayur.pdm;

import java.util.Date;

/**
 * Created by mayur on 4/22/2018.
 */

public class Message {
    private String content;
    private long messageTime;

    public Message(){

    }
    public Message(String content){
        this.content=content;
        messageTime = new Date().getTime();
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public long getMessageTime(){return  messageTime;}

    public void  setMessageTime(long messageTime){this.messageTime = messageTime;}
}
