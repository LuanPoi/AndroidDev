package com.example.loginegaleria;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class GalleryItem {
    private final String title;
    private final Drawable imageResource;

    public GalleryItem(String title, Drawable imageResource){
        this.title = title;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public Drawable getImageResource() {
        return imageResource;
    }
}
