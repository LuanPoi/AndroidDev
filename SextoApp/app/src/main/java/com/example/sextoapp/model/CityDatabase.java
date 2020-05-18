package com.example.sextoapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CityDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "cities.sqlite";
    private static final String DB_TABLE = "cities";
    private static final String COL_ID = "_id";
    private static final String COL_NAME = "name";
    private static final String COL_POPULATION = "population";
    private static final int DB_VERSION = 1;

    private Context context;

    public CityDatabase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    /*CRUD
    CREATE - CRIA DADOS
    RETRIVE - PEGA OS DADOS
    UPDATE - MODIFICA OS DADOS
    DELETE - DELETA OS DADOS
    */

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table if not exists " + DB_TABLE + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " TEXT, " +
                COL_POPULATION + " INTEGER);";

        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //CREATE - CRIA DADOS
    public long createCityInDatabase(City city){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NAME, city.getName());
        values.put(COL_POPULATION, city.getPopulation());

        long id = db.insert(DB_TABLE, "", values);
        city.setId(id);

        db.close();
        return id;
    }
    //RETRIVE - PEGA OS DADOS
    public List<City> retrieveCitiesFromDatabase(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                DB_TABLE,
                null, null, null, null, null, COL_NAME
        );
        List<City> cities = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                City city = new City(
                        cursor.getString(cursor.getColumnIndex(COL_NAME)),
                        cursor.getInt(cursor.getColumnIndex(COL_POPULATION))
                );
                city.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));

                cities.add(city);
            }while (cursor.moveToNext());
        }

        db.close();
        return cities;
    }
    //UPDATE - MODIFICA OS DADOS
    public int updateCityFromDatabase(City city){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        String _id = String.valueOf(city.getId());

        values.put(COL_NAME, city.getName());
        values.put(COL_POPULATION, city.getPopulation());

        int count = db.update(DB_TABLE, values, COL_ID + "=?", new String[]{_id});

        db.close();
        return count;
    }
    //DELETE - DELETA OS DADOS
    public int removeCityFromDatabase(City city){
        SQLiteOpenHelper dbHelper;
        SQLiteDatabase db = getWritableDatabase();
        String _id = String.valueOf(city.getId());

        int count = db.delete(DB_TABLE, COL_ID + "=?", new String[]{_id});

        db.close();
        return count;
    }

    //ORDER BY
    public List<City> retrieveCitiesFromDatabase(String orderBy){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                DB_TABLE,
                null, null, null, null, null, orderBy, COL_NAME
        );
        List<City> cities = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                City city = new City(
                        cursor.getString(cursor.getColumnIndex(COL_NAME)),
                        cursor.getInt(cursor.getColumnIndex(COL_POPULATION))
                );
                city.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));

                cities.add(city);
            }while (cursor.moveToNext());
        }

        db.close();
        return cities;
    }
}
