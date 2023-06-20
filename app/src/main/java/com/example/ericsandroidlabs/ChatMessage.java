package com.example.ericsandroidlabs;

/**This page represents the first page loaded
 * @author Eric TOrunski
 * @version 1.0
 */

public class ChatMessage
{
    String message;
    int sendOrReceive;
    String timeSent;
    public ChatMessage(String s,
                       String time,
                       int type){
        message = s;
        timeSent = time;
        sendOrReceive = type;
    }
}
