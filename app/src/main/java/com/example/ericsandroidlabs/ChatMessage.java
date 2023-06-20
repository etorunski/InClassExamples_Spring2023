package com.example.ericsandroidlabs;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**This page represents the first page loaded
 * @author Eric TOrunski
 * @version 1.0
 */
@Entity
public class ChatMessage
{
    @PrimaryKey(autoGenerate = true) //increment the ids for us
    @ColumnInfo(name="id")
    public long id;

    @ColumnInfo(name="Message")
    String message;

    @ColumnInfo(name="SendOrRecieve")
    int sendOrReceive;

    @ColumnInfo(name="TimeSent")
    String timeSent;

    public ChatMessage( String s,
                       String time,
                       int type){

        message = s;
        timeSent = time;
        sendOrReceive = type;
    }

    public ChatMessage()//for database queries
    {}
}
