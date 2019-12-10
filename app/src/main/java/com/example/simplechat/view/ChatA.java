package com.example.simplechat.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.simplechat.R;
import com.example.simplechat.util.Constants;

import java.util.ArrayList;

public class ChatA extends AppCompatActivity {

    int chatLength = 0;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;
    LinearLayout linearScroll;
    LinearLayout.LayoutParams textViewParams;

    ImageView userImage;
    EditText chatText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_a);
        sharedPreferences = getSharedPreferences(Constants.SPREF, Context.MODE_PRIVATE);
        spEditor = sharedPreferences.edit();
        chatLength = sharedPreferences.getInt(Constants.CHAT_LENGTH, 0);
        linearScroll = findViewById(R.id.linearScroll);
        chatText = findViewById(R.id.chatText);
        textViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        ArrayList<String> messages = new ArrayList<>();

        for(int i = 0; i <= chatLength; i++) {
            String message = sharedPreferences.getString(Constants.CHAT_MESSAGE + i, "");
            messages.add(message);
            createTextView(message);
        }

        userImage = findViewById(R.id.imageView);
        Glide.with(this)
                .load(getDrawable(R.drawable.ic_person_blue_24dp))
                .into(userImage);
        //testFun();
    }

    private void testFun() {
        createTextView("This is a message");
        createTextView("This is a test");
    }

    private void createTextView(String message) {
        TextView newMessage = new TextView(this);
        newMessage.setLayoutParams(textViewParams);
        newMessage.setText(message);
        linearScroll.addView(newMessage);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Constants.CHAT_A_TAG, "onPause: pausing now");
        spEditor.commit();
    }

    public void onSend(View v) {
        if(v.getId() == R.id.sendButton) {
            String message = chatText.getText().toString().trim();
            if(!message.isEmpty()) {
                message = "A: " + message;
                createTextView(message);
                spEditor.putInt(Constants.CHAT_LENGTH, ++chatLength);
                spEditor.putString(Constants.CHAT_MESSAGE + chatLength, message);
                chatText.setText("");
                Log.d(Constants.CHAT_A_TAG, "onSend: " + chatLength);
            }
        }
    }
}
