package com.example.ericsandroidlabs;


import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ericsandroidlabs.databinding.MessageDetailsBinding;

public class DetailsFragment extends Fragment {

    ChatMessage thisMessage;

    public DetailsFragment(ChatMessage m)
    {
        thisMessage = m;
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);


        MessageDetailsBinding binding = MessageDetailsBinding.inflate(inflater, container, false);

        binding.messageText.setText( thisMessage.message);
        binding.timeText.setText( thisMessage.timeSent  );
        binding.idText.setText( "" + thisMessage.id );

        return binding.getRoot();
    }
}
