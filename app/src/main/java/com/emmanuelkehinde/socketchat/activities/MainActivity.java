package com.emmanuelkehinde.socketchat.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emmanuelkehinde.socketchat.R;
import com.emmanuelkehinde.socketchat.adapters.ChatAdapter;
import com.emmanuelkehinde.socketchat.models.Chat;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView chatRecyc;
    private ChatAdapter chatAdapter;

    private EditText edt_message;
    private String username;
    private Socket mSocket;
    private static final String BASE_URL = "BASE_URL_HERE";

    {
        try {
            mSocket = IO.socket(BASE_URL);
        } catch (URISyntaxException e) {}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=getIntent().getStringExtra("username");

        chatRecyc=findViewById(R.id.chatList);
        edt_message=findViewById(R.id.edt_message);
        Button btn_send = findViewById(R.id.btn_send);

        chatAdapter=new ChatAdapter(this);
        chatRecyc.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        chatRecyc.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        chatRecyc.setAdapter(chatAdapter);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edt_message.getText().toString())){
                    Toast.makeText(MainActivity.this, "Enter a chat message", Toast.LENGTH_SHORT).show();
                }else sendMessage();
            }
        });

        mSocket.connect();
        mSocket.on("chat message", onNewMessage);
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    Chat chat = null;
                    try {
                        chat.setMsg(data.getString("msg"));
                        chat.setSender(data.getString("sender"));
                    } catch (JSONException e) {
                        return;
                    }
                    chatAdapter.addChat(chat);
                    chatRecyc.scrollToPosition(chatAdapter.getItemCount()-1);
                }
            });
        }
    };


    private void sendMessage() {
        edt_message.setText("");
        String message=edt_message.getText().toString();

        mSocket.emit("chat message", new Chat(message,username));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
        mSocket.off("chat message", onNewMessage);
    }
}
