package com.example.ericsandroidlabs.data;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ericsandroidlabs.ChatMessage;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {
    public ArrayList<ChatMessage> theWords = new ArrayList<>();
    public MutableLiveData<ChatMessage> chosenMessage = new MutableLiveData<ChatMessage>();
}
