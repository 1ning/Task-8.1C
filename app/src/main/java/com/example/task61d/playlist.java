package com.example.task61d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.task61d.Database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class playlist extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        dbHelper = new DatabaseHelper(this, "database", null, 1);
        db = dbHelper.getWritableDatabase();
        List<String> list = queryAllTrucksData();
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (this != null) {
            playlistAdapter playlistAdapter = new playlistAdapter(this, list);
            mRecyclerView.setAdapter(playlistAdapter);
        }
    }

    public List<String> queryAllTrucksData(){
        List<String> list;
        try ( Cursor cursor = db.query("playlist", null, "userid=?", new String[]{Global.id}, null, null, null);) {
            list = new ArrayList<>();
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    String context = cursor.getString(cursor.getColumnIndexOrThrow("context"));
                    list.add(context);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        db.close();
        return list;
    }
}
