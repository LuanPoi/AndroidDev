package com.example.loginegaleria;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    TextView textViewImageTitleLabel;
    TextView textViewIndexLabel;
    Button buttonNextImage;
    Button buttonPreviousImage;
    Switch switchImageVisibility;
    ImageView imageViewImageContent;

    int gallerySize = 0;
    int currentPosition = 0;

    private List<GalleryItem> galleryItems = new ArrayList<GalleryItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        textViewImageTitleLabel = findViewById(R.id.textViewImageTitleLabel);
        textViewIndexLabel = findViewById(R.id.textViewIndexLabel);
        buttonNextImage = findViewById(R.id.buttonNextImage);
        buttonPreviousImage = findViewById(R.id.buttonPreviousImage);
        switchImageVisibility = findViewById(R.id.switchImageVisibility);
        imageViewImageContent = findViewById(R.id.imageViewImageContent);

        imageViewImageContent.setVisibility(View.GONE);
        imageViewImageContent.setAlpha(1f);
        imageViewImageContent.setVisibility(View.VISIBLE);


        for (int j = 1; j < 14; j++) {
            String name = "phone_wallpaper_"+j;
            galleryItems.add(
                    new GalleryItem(
                        name,
                        getResources().getDrawable(
                                getResources().getIdentifier(
                                        name,
                                        "drawable",
                                        getPackageName()
                                )
                        )
                    )
            );
        }
        gallerySize = galleryItems.size();

        currentPosition = 1;
        updateScreenWithNewGalleryItem();

        switchImageVisibility.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        int shortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);

                        if(isChecked == true){
                            imageViewImageContent.animate()
                                    .alpha(1f)
                                    .setDuration(shortAnimationDuration)
                                    .setListener(null);
                        }else{
                            imageViewImageContent.animate()
                                    .alpha(0f)
                                    .setDuration(shortAnimationDuration)
                                    .setListener(null);

                        }
                    }
                }
        );
    }

    private void updateScreenWithNewGalleryItem(){
        textViewImageTitleLabel.setText(galleryItems.get(currentPosition-1).getTitle());
        textViewIndexLabel.setText(currentPosition + "/" + gallerySize);
        imageViewImageContent.setImageDrawable(galleryItems.get(currentPosition-1).getImageResource());
    }

    public void buttonNextImagePressed(View v){
        if(currentPosition < galleryItems.size()){
            ++currentPosition;
            updateScreenWithNewGalleryItem();
        }
    }

    public void buttonPreviousImagePressed(View v){
        if(currentPosition > 1){
            --currentPosition;
            updateScreenWithNewGalleryItem();
        }
    }
}
