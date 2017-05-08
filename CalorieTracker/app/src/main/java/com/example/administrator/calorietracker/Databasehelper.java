package com.example.administrator.calorietracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Navya on 4/7/2017.
 */

public class Databasehelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Calorietracker.db";
    private static final String TABLE_NAME = "Users";
    private static final String Column_id = "id";
    private static final String Column_Uname = "name";
    private static final String  Column_pass = "pass";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table Users (id integer primary key not null," +
            "name text not null,pass text not null)";

    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db= db;
    }

    public void insertUser(User u){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from Users";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();

        values.put(Column_id,count);
        values.put(Column_Uname,u.getname());
        values.put(Column_pass,u.getPass());
        db.insert(TABLE_NAME,null,values);
    }

    public String searchPass(String name){
        db= this.getReadableDatabase();
        String query = "select name ,pass from Users";
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        b= "not found";
        if(cursor.moveToFirst()){

            do{
                a = cursor.getString(0);
                b = cursor.getString(1);
                if(a.equals(name)){
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
    return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String query = "DROP TABLE IF EXISTS" +TABLE_NAME;
            db.execSQL(query);
            this.onCreate(db);
    }
}
