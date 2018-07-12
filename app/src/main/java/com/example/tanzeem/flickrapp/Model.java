package com.example.tanzeem.flickrapp;

// This model class will be used to provide data to list view after parsing the json.
public class Model {
    String thumbnailUrl;

    public Model() {
    }

    public Model(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    //Getter
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    //Setter
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
