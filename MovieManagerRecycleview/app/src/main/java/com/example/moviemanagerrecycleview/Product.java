package com.example.moviemanagerrecycleview;

import android.net.Uri;

import androidx.annotation.Nullable;

public class Product {
    String name;
    int rating;
    Uri image;

    public Product(String name, int rating, Uri image) {
        this.name = name;
        this.rating = rating;
        this.image = image;
    }



}
