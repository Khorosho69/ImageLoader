package com.antont.imageloader.models;

import android.graphics.drawable.Drawable;

public class ImageItem {
    private  String imageURL;

    private Drawable mImage;
    public ImageItem(String imageURL) {
        this.imageURL = imageURL;
    }

    public Drawable getImage() {
        return mImage;
    }

    public void setImage(Drawable image) {
        mImage = image;
    }

    public String getImageURL() {
        return imageURL;
    }
}
