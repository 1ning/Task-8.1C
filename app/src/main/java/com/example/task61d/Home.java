package com.example.task61d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.task61d.Database.DatabaseHelper;

public class Home extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    Button add, playlist, play;
    EditText url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        play = findViewById(R.id.play);
        playlist = findViewById(R.id.playlist);
        add = findViewById(R.id.add);
        url = findViewById(R.id.url);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String url1 = url.getText().toString();
                if (url == null) {
                    Toast.makeText(Home.this, "URL cannot be empty, please re-enter", Toast.LENGTH_SHORT).show();
                }
                dbHelper = new DatabaseHelper(Home.this, "database", null, 1);
                db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("context", url1);
                values.put("userid", Global.id);
                long result = db.insert("playlist", null, values);
                if (result > 0) {
                    Toast.makeText(Home.this, "add successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,playlist.class);
                startActivity(intent);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(Home.this,play.class);
                startActivity(intent2);
            }
        });
    }
}
