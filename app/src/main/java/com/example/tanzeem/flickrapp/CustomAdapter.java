package com.example.tanzeem.flickrapp;

//This class provide data to the list view.

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class CustomAdapter extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private List modelItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    //Constructor
    public CustomAdapter(Activity activity, List modelItems) {
        this.activity = activity;
        this.modelItems = modelItems;
    }

    //Some methods which are must to be implemented.
    @Override
    public int getCount() {
        return modelItems.size();
    }

    @Override
    public Object getItem(int location) {
        return modelItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int positon, View convertView, ViewGroup viewGroup) {
        //Condition to check if inflator is set or not
        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Condition to check if view is set or not
        if (convertView == null)
            convertView = inflater.inflate(R.layout.photo_item,null);

        //Condition to check if imageloader is set or not
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        //Taking the reference
        NetworkImageView thumbNail = convertView.findViewById(R.id.thumbnail);

        //Getting the model data for the row
        Model model = (Model) modelItems.get(positon);

        //Thumbnail image
        thumbNail.setImageUrl(model.getThumbnailUrl(),imageLoader);

        return convertView;
    }
}
