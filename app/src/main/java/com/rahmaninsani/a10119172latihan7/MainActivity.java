package com.rahmaninsani.a10119172latihan7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.rahmaninsani.a10119172latihan7.adapter.MessageAdapter;
import com.rahmaninsani.a10119172latihan7.model.MessageModel;
import com.rahmaninsani.a10119172latihan7.service.MessageService;
import com.rahmaninsani.a10119172latihan7.service.GetService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
    NIM     : 10119172
    Nama    : Rahman Insani
    Kelas   : IF-5

    Kamis, 14 Juli 2022
*/


public class MainActivity extends AppCompatActivity  {

    MessageAdapter adapter;
    List<Map<Integer, String>> data = new ArrayList<>();
    private ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScrollView = findViewById(R.id.scrollview_recyclerview_message);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_message);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new MessageAdapter(MainActivity.this, data);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.button_send).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               EditText tvText = findViewById(R.id.edittext_message);
               String myMessage = tvText.getText().toString();

               addMyMessage(myMessage);
               tvText.getText().clear();

               addResponseMessage(myMessage);

           }
        });
    }

    private void addMyMessage(String myMessage) {
        addMessageView(1, myMessage);
    }

    private void addResponseMessage(String myMessage) {

        GetService messageService = MessageService.getRetrofitInstance().create(GetService.class);

        try {
            myMessage = URLEncoder.encode(myMessage, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            Log.e("Error", "Error");
        }

        Call<MessageModel> call = messageService.getResponse(myMessage);

        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(@NonNull Call<MessageModel> call, @NonNull Response<MessageModel> response) {
                assert response.body() != null;
                String responseMessage = response.body().getResponseMessage();
                addMessageView(2, responseMessage);
            }

            @Override
            public void onFailure(@NonNull Call<MessageModel> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addMessageView(Integer id, String message) {
        int lastIndex = adapter.getItemCount();

        Map<Integer, String> messageItem = new HashMap<>();
        messageItem.put(id, message);

        data.add(lastIndex, messageItem);
        adapter.notifyItemInserted(lastIndex);

        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.smoothScrollTo(0, mScrollView.getHeight());
            }
        });
    }
}