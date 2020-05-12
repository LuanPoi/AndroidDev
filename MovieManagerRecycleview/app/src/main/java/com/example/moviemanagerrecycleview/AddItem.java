package com.example.moviemanagerrecycleview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddItem extends AppCompatActivity {
    ImageButton imageButtonMovie;
    EditText editTextNameInput;
    EditText editTextRatingInput;
    Uri image;

    static int CAMERA_INTENT_CODE = 101;
    static int CAMERA_PERMISION_CODE = 201;
    String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        imageButtonMovie = findViewById(R.id.imageButtonMovie);
        editTextNameInput = findViewById(R.id.editTextNameInput);
        editTextRatingInput = findViewById(R.id.editTextRatingInput);

        imageButtonMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    requestCameraPermission();
                }else{
                    sendCameraIntent();

                }

            }
        });
    }


    public void addButtonPressed(View v){
        if(!editTextNameInput.getText().toString().isEmpty() && !editTextRatingInput.getText().toString().isEmpty() && image != null){
            if(Integer.parseInt(editTextRatingInput.getText().toString()) >= 0 && Integer.parseInt(editTextRatingInput.getText().toString()) <= 5){
                //Product movie = new Product(editTextNameInput.getText().toString(), Integer.parseInt(editTextRatingInput.getText().toString()), image);
                Intent resultIntent = new Intent();
                // TODO Add extras or a data URI to this intent as appropriate.
                resultIntent.putExtra("resultName", editTextNameInput.getText().toString());
                resultIntent.putExtra("resultRating", editTextRatingInput.getText().toString());
                resultIntent.putExtra("resultImage", image.toString());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void requestCameraPermission(){
        if(getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_ANY)){
            if(checkSelfPermission(Manifest.permission.CAMERA) !=
                    PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{
                        Manifest.permission.CAMERA
                },CAMERA_PERMISION_CODE);
            }else{
                sendCameraIntent();
            }
        }else{
            Toast.makeText(AddItem.this,
                    "No camera available",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISION_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                sendCameraIntent();
            }else{
                Toast.makeText(AddItem.this,
                        "Camera permission denied",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    void sendCameraIntent(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION,true);
        if(intent.resolveActivity(getPackageManager())!=null){
            File pictureFile = null;
            try {
                String timeStamp = new
                        SimpleDateFormat("yyyyMMddHHmmss")
                        .format(new Date());
                String picName = "pic_" + timeStamp;
                File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                pictureFile = File.createTempFile(picName, ".jpg", dir);
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(AddItem.this,
                        "Photo file could not be created",
                        Toast.LENGTH_LONG
                ).show();
            }
            if(pictureFile != null){
                picturePath = pictureFile.getAbsolutePath();
                Uri photoURI = FileProvider.
                        getUriForFile(AddItem.this,
                                "com.example.moviemanagerrecycleview.fileprovider",
                                pictureFile
                        );
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                startActivityForResult(intent,CAMERA_INTENT_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_INTENT_CODE){
            if(resultCode != RESULT_OK){
                Toast.makeText(AddItem.this,
                        "Problems getting the image from the camera",
                        Toast.LENGTH_LONG)
                        .show();
            }else{
                File file = new File(picturePath);
                if(file.exists()){
                    imageButtonMovie.setImageURI(Uri.fromFile(file));
                    this.image = Uri.fromFile(file);
                }
            }
        }
    }


}
