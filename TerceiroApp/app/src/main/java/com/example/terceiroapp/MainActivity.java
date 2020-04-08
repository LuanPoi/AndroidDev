package com.example.terceiroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    ListView listViewCompras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFromFile();
        listViewCompras = findViewById(R.id.listViewCompras);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                DataModel.getInstance().lista
        );

        listViewCompras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = DataModel.getInstance().lista.get(position);
                s = s + " Clicked";
                DataModel.getInstance().lista.set(position, s);
                updateListViewCompras();
            }
        });
        listViewCompras.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DataModel.getInstance().lista.remove(position);
                updateListViewCompras();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListViewCompras();
    }

    int contador = 0;
    public void buttonAddClicked(View v){
        contador++;
        DataModel.getInstance().lista.add("Item"+contador);
        updateListViewCompras();
    }

    void updateListViewCompras(){
        saveToFile();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                DataModel.getInstance().lista
        );
        listViewCompras.setAdapter(adapter);
    }

    void loadFromFile(){
        try {
            InputStream stream = MainActivity.this.openFileInput("lista.txt");
            InputStreamReader streamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            while ((line = reader.readLine()) != null){
                DataModel.getInstance().lista.add(line);
            }
            reader.close();
            streamReader.close();
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void saveToFile(){
        try {
            OutputStream stream = MainActivity.this.openFileOutput(
                    "lista.txt",
                    this.MODE_PRIVATE
            );
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            for (int i = 0; i < DataModel.getInstance().lista.size(); i++){
                String s = DataModel.getInstance().lista.get(i);
                writer.write(s);
                writer.write("\n");
            }
            writer.flush();
            writer.close();
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
