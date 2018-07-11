package com.example.tanzeem.flickrapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class PhotoRecyclerView extends RecyclerView.Adapter<PhotoRecyclerView.MyViewHolder> {

    private Context photoContext;
    private List<PhotoGetterSetter> photoData;

    public PhotoRecyclerView(Context photoContext, List<PhotoGetterSetter> photoData) {
        this.photoContext = photoContext;
        this.photoData = photoData;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater photoinflator = LayoutInflater.from(photoContext);
        view = photoinflator.inflate(R.layout.photo_item,parent,false);
        return new PhotoRecyclerView.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.photothumbnail.setImageResource(photoData.get(position).getThumbnail());


    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView photothumbnail;
        public MyViewHolder(View itemView) {
            super(itemView);
            photothumbnail = itemView.findViewById(R.id.album_image);
        }
    }
}
