package com.rahmaninsani.a10119172latihan7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahmaninsani.a10119172latihan7.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
    NIM     : 10119172
    Nama    : Rahman Insani
    Kelas   : IF-5

    Kamis, 14 Juli 2022
*/

public class MessageAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MY_MESSAGE = 1;
    private static final int VIEW_TYPE_RESPONSE_MESSAGE = 2;

    private final List<Map<Integer, String>> mData;
    private final LayoutInflater mInflater;

    public MessageAdapter(Context context, List<Map<Integer, String>> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        List<Integer> data = new ArrayList<>(mData.get(position).keySet());
        Integer list = data.get(0);

        if (list == 1) {
            // My Message
            return VIEW_TYPE_MY_MESSAGE;
        } else {
            // Response Message
            return VIEW_TYPE_RESPONSE_MESSAGE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == 1) {
            view = mInflater.inflate(R.layout.item_my_message, parent, false);
            return new MyMessageHolder(view);
        }

        view = mInflater.inflate(R.layout.item_response_message, parent, false);
        return new ResponseMessageHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        List<String> data = new ArrayList<>(mData.get(position).values());
        String text = data.get(0);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MY_MESSAGE:
                ((MyMessageHolder) holder).bind(text);
                break;
            case VIEW_TYPE_RESPONSE_MESSAGE:
                ((ResponseMessageHolder) holder).bind(text);
        }
    }

    private static class MyMessageHolder extends RecyclerView.ViewHolder  {
        TextView textView;

        MyMessageHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview_my_message);
        }

        void bind(String message) {
            textView.setText(message);
        }
    }

    private static class ResponseMessageHolder extends RecyclerView.ViewHolder  {
        TextView textView;

        ResponseMessageHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview_response_message);
        }

        void bind(String message) {
            textView.setText(message);
        }
    }

}
