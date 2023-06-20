package com.example.ericsandroidlabs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ChatMessageDAO {

    @Insert
    public long anyFunctionNameForInsertion(ChatMessage messgeToInsert);


    @Update
    public int anyUpdate(ChatMessage updatedMessage);

                        //@Entity
    @Query("Select * from ChatMessage")//SQL Statement
    public List<ChatMessage> getAllMessages();

    @Delete
    public int deleteThisChatMessage(ChatMessage cm);
}
