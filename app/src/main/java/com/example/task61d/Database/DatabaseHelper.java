package com.example.task61d.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String sqlStatements = "create table user ("
            + "id integer primary key autoincrement, "
            + "username text, "
            + "password text, "
            + "fullname text)";
    private static final String CREATE_playlist = "create table playlist ("
            + "id integer primary key autoincrement,"
            + "userid text, "
            + "context text)";


    private Context context;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlStatements);
        db.execSQL(CREATE_playlist);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
