package com.example.loginegaleria;

import android.widget.ImageView;

public class GalleryItem {
    private final String title;
    private final ImageView imageResource;

    public GalleryItem(String title, ImageView imageResource){
        this.title = title;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public ImageView getImageResource() {
        return imageResource;
    }
}
