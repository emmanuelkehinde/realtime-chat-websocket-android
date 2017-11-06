package com.emmanuelkehinde.ssetest.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emmanuelkehinde.ssetest.R;

public class IntroActivity extends AppCompatActivity {

    private EditText edt_username;
    private Button btn_proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        edt_username=findViewById(R.id.edt_username);
        btn_proceed=findViewById(R.id.btn_proceed);

        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edt_username.getText().toString())){
                    Toast.makeText(IntroActivity.this, "Create a username", Toast.LENGTH_SHORT).show();
                }else proceed();
            }
        });
    }

    private void proceed() {
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("username",edt_username.getText().toString());
        startActivity(new Intent(intent));
    }
}
