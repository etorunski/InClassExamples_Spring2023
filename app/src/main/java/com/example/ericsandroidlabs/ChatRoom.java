package com.example.ericsandroidlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ericsandroidlabs.data.ChatRoomViewModel;
import com.example.ericsandroidlabs.databinding.ActivityChatRoomBinding;
import com.example.ericsandroidlabs.databinding.ReceiveRowBinding;
import com.example.ericsandroidlabs.databinding.SentRowBinding;

import java.util.ArrayList;

public class ChatRoom extends AppCompatActivity {

    ArrayList<String> messages ;
    ActivityChatRoomBinding binding;
RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChatRoomViewModel chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);;

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //initialize to the ViewModel arraylist:
        messages = chatModel.messages;

        binding.submit.setOnClickListener(click -> {

            String typed = binding.message.getText().toString();
            messages.add(typed);

            //notify the adapter:
            myAdapter.notifyItemInserted( messages.size() -1 );//redraw the missing row

            binding.message.setText("");//remove what was typed
        });
        binding.theRecycleView.setAdapter(myAdapter = new RecyclerView.Adapter<RowHolder>() {
            @NonNull
            @Override
            public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//viewType is 0 or 1 based on getItemViewType(int position)
                if(viewType == 0) {
                    SentRowBinding rowBinding = SentRowBinding.inflate(getLayoutInflater(), parent, false);


                    //this will initialize the row variables:
                    return new RowHolder(rowBinding.getRoot());
                }
                else{
                    ReceiveRowBinding rowBinding = ReceiveRowBinding.inflate(getLayoutInflater(), parent, false);

                    return new RowHolder(rowBinding.getRoot());
                }
            }

            @Override
            public void onBindViewHolder(@NonNull RowHolder holder, int position) {

                String message = messages.get(position);
                //override the text in the rows:
                holder.message.setText(message);
                holder.time.setText("5:00pm");
            }

            //the number of items:
            @Override
            public int getItemCount() {
                return messages.size();
            }
            @Override

            public int getItemViewType(int position)
            {
                //which layout to use for object at position?
                String message = messages.get(position);
                if(position % 2 == 0) //is position even
                {
                    return 0;
                }
                else return 1;
            }
        });

        binding.theRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    //this represents one row
    public class RowHolder extends RecyclerView.ViewHolder{

        ArrayList<String> messages = new ArrayList<>();
        TextView message;
        TextView time;

        //just initialize the variables
        public RowHolder(@NonNull View itemView) {
            super(itemView);

            message = itemView.findViewById(R.id.theMessage);
            time = itemView.findViewById(R.id.theTime);
        }
    }
}