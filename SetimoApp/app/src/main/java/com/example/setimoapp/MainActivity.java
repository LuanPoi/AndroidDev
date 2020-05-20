package com.example.setimoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    TextView textViewWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewWeb = findViewById(R.id.textViewWeb);
        Context context;
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://www.google.com";
        StringRequest stringRequest = new StringRequest(
            Request.Method.GET,
            url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    textViewWeb.setText("response:\n\"+response.substring(0, 500));
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    textViewWeb.setText("Did not work");
                }
            }
        );
        queue.add(stringRequest);
    }
}
