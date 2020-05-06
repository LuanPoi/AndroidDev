package com.example.quintoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
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

        products.add(new Product("banana", true));
        products.add(new Product("alcool gel", true));
        products.add(new Product("maça", true));
        products.add(new Product("uva", true));

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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_add_item){
            products.add(0, new Product("item", false));
            //productsAdapter.notifyDataSetChanged();
            productsAdapter.notifyItemInserted(0);
            View contextView = findViewById(android.R.id.content);
            Snackbar.make(contextView, "Item added", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    products.remove(0);
                    productsAdapter.notifyItemRemoved(0);
                }
            }).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
