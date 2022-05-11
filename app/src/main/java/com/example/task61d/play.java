package com.example.task61d;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.task61d.Database.DatabaseHelper;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class play extends YouTubeBaseActivity {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    YouTubePlayerView player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        player=findViewById(R.id.player);
        dbHelper = new DatabaseHelper(play.this, "database", null, 1);
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("playlist", null, "userid=?", new String[]{Global.id}, null, null, null);
        List<String> list2 = new ArrayList<>();
      if(cursor.getCount() > 0) {
          cursor.moveToFirst();
          for (int i = 0; i < cursor.getCount(); i++) {
              String context = cursor.getString(cursor.getColumnIndexOrThrow("context"));
              String[] temp2;
              String[] temp;
              String delimeter = "=";
              temp2 = context.split(delimeter);
                 if(temp2.length > 1) {
                  delimeter = temp2[1];
                  String delimeter2 = "&";
                  temp = delimeter.split(delimeter2);
                  list2.add(temp[0]);
                  cursor.moveToNext();
              }
          }
      }
        YouTubePlayer.OnInitializedListener listener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideos(list2);
                youTubePlayer.play();
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(),
                        "InitializationFailure",
                        Toast.LENGTH_LONG).show();
            }
        };
        player.initialize(YouTubeConfig.getApiKey(),listener);
    }
}