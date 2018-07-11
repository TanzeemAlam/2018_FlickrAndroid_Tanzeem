package com.example.tanzeem.flickrapp;

class PhotoGetterSetter {

    private String Thumbnail;

    public PhotoGetterSetter(String thumbnail) {
        Thumbnail = thumbnail;
    }

    //Getter

    public int getThumbnail() {
        return Integer.parseInt(Thumbnail);
    }
    //Setter


    public void setThumbnail(int thumbnail) {
        Thumbnail = String.valueOf(thumbnail);
    }
}
