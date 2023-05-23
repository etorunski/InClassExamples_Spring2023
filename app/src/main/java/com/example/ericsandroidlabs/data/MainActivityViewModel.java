package com.example.ericsandroidlabs.data;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//correspond to what's on MainActivity
public class MainActivityViewModel extends ViewModel {

    public String theText = "HelloWorld";
    public MutableLiveData<Boolean>  isOn = new MutableLiveData<>(false);
}
