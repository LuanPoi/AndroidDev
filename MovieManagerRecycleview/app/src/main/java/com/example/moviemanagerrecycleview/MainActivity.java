package com.example.moviemanagerrecycleview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Product> products = new ArrayList<>();
    RecyclerView listRecycleView;
    ProductsAdapter productsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listRecycleView = findViewById(R.id.listRecycleView);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        listRecycleView.addItemDecoration(itemDecoration);

        //products.add(new Product("Maçã", 2, images[0]));
        //products.add(new Product("Alcool gel", 5, images[1]));
        productsAdapter = new ProductsAdapter(products);

        listRecycleView.setAdapter(productsAdapter);
        listRecycleView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_add_item){
            //products.add(0,new Product("item", false));
            productsAdapter.notifyItemInserted(0);
            //productsAdapter.notifyDataSetChanged();
            //R.id.content = view pai de TODAS as views
            View contextView = findViewById(android.R.id.content);
            Snackbar.make(contextView, "Item added", Snackbar.LENGTH_LONG)
                    .setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            products.remove(0);
                            productsAdapter.notifyItemRemoved(0);
                        }
                    })
                    .show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void floatingActionButtonAddMoviePressed(View v){
        startActivityForResult(new Intent(MainActivity.this, AddItem.class), 69);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (69) : {
                if (resultCode == Activity.RESULT_OK) {
                    products.add(0, new Product(data.getStringExtra("resultName"), Integer.parseInt(data.getStringExtra("resultRating")), Uri.parse(data.getStringExtra("resultImage"))));
                    productsAdapter.notifyItemInserted(0);
                }
                break;
            }
        }
    }

}
