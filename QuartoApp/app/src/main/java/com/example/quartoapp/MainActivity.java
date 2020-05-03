package com.example.quartoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageView imageViewUserImage;
    Button buttonUserImage;
    static int CAMERA_INTENT_CODE = 101;
    static int CAMERA_PERMISSION_CODE = 201;
    String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewUserImage = findViewById(R.id.imageViewUserImage);
        buttonUserImage = findViewById(R.id.buttonUserImage);

        buttonUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    requestCameraPermission();
                }else{
                    sendCameraIntent();
                }
                sendCameraIntent();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void requestCameraPermission(){
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{
                    Manifest.permission.CAMERA
                }, CAMERA_PERMISSION_CODE);
            }else{
                sendCameraIntent();
            }
        }else{
            Toast.makeText(MainActivity.this,
                    "No camera avaliable",
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                sendCameraIntent();
            }else{
                Toast.makeText(MainActivity.this,
                        "Camera permission denied",
                        Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    void sendCameraIntent(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true);

        if(intent.resolveActivity(getPackageManager()) != null){
            File pictureFile = null;
            try {
                String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String picName = "pic_" + timeStamp;
                File dir = getExternalFilesDirs(Environment.DIRECTORY_PICTURES)[0];
                pictureFile = File.createTempFile(picName, ".jpg", dir);
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(MainActivity.this,
                        "Photo file could not be created",
                        Toast.LENGTH_LONG
                        ).show();
            }
            if(pictureFile != null){
                picturePath = pictureFile.getAbsolutePath();
                Uri photoURI = FileProvider
                        .getUriForFile(MainActivity.this,
                                "com.example.quartoapp.file_provider",
                                pictureFile
                        );
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, CAMERA_INTENT_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_INTENT_CODE){
            if(resultCode != RESULT_OK){
                Toast.makeText(MainActivity.this,
                        "Problems getting the image from the camera",
                        Toast.LENGTH_LONG)
                        .show();
            }else{
                File file = new File(picturePath);
                if(file.exists()){
                    imageViewUserImage.setImageURI(Uri.fromFile(file));
                }
            }
        }
    }
}
