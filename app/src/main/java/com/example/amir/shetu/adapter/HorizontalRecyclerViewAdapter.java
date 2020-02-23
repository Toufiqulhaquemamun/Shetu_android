package com.example.amir.shetu.adapter;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.amir.shetu.R;

import java.io.File;
import java.util.ArrayList;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.MessageViewHolder> {

    private ArrayList<Uri> arrayList;
    private Context context;
    private File file;

    public HorizontalRecyclerViewAdapter(ArrayList<Uri> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public HorizontalRecyclerViewAdapter(File file, Context context) {
        this.file=file;
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
//        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return  super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(final MessageViewHolder holder, final int position) {
        Glide.with(context)
                .load(new File(arrayList.get(position).getPath()))
                .into(holder.imageView);

//        holder.imageView.setImageURI(arrayList.get(position));


    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        private MessageViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
        }
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MessageViewHolder(itemView);
    }
}
