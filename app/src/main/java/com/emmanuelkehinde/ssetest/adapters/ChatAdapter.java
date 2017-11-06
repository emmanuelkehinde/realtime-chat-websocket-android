package com.emmanuelkehinde.ssetest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emmanuelkehinde.ssetest.R;
import com.emmanuelkehinde.ssetest.models.Chat;

import java.util.ArrayList;

/**
 * Created by emmanuel.kehinde on 23/10/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{

    public Context context;
    private ArrayList<Chat> chatList = new ArrayList<>();


    public ChatAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_chat,parent,false);

        return new ChatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        Chat chat = chatList.get(position);
        holder.txt_name.setText(chat.getSender());
        holder.txt_message.setText(chat.getMsg());
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public void addChat(Chat chat) {
        chatList.add(chat);
        notifyDataSetChanged();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {

        TextView txt_name;
        TextView txt_message;
        public ChatViewHolder(View itemView) {
            super(itemView);
            txt_name=itemView.findViewById(R.id.txt_name);
            txt_message=itemView.findViewById(R.id.txt_message);
        }
    }
}
