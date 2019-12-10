package com.example.simplechat.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.simplechat.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        switch (v.getId()) {
            case R.id.chat_a_button:
                intent = new Intent(this, ChatA.class);
                break;
            case R.id.chat_b_button:
                intent = new Intent(this, ChatB.class);
                break;
        }
        startActivity(intent);
    }
}
