package com.antont.imageloader.architecture_components;

import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.Drawable;

import com.antont.imageloader.models.ImageItem;

import java.util.ArrayList;
import java.util.List;

public class ImageItemsViewModel extends ViewModel {

    private List<ImageItem> mImageItems;

    private Drawable mImage;

    public void addImageItem(ImageItem item) {
        mImageItems.add(item);
    }

    public List<ImageItem> getImageItems() {
        if (mImageItems == null) {
            mImageItems = new ArrayList<>();
        }
        return mImageItems;
    }

    public Drawable getImage() {
        return mImage;
    }

    public void setImage(Drawable image) {
        mImage = image;
    }
}
