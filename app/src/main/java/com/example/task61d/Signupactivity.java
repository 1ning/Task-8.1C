package com.example.task61d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.task61d.Database.DatabaseHelper;

public class Signupactivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    EditText Username;
    EditText Password;
    EditText Fullname;
    private Bitmap head;
    EditText ConfirmPassword;
    Button Create;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        findid();;
        dbHelper = new DatabaseHelper(this, "database", null, 1);
        Create.setOnClickListener(new View.OnClickListener() {
            String username;
            String password2;
            String fullname;
            String password;
            public void onClick(View view) {
                username = Username.getText().toString().trim();
                password = Password.getText().toString().trim();
                password2 = ConfirmPassword.getText().toString().trim();
                fullname = Fullname.getText().toString().trim();
                if (username.length()<5) {
                    Toast.makeText(Signupactivity.this, "Username not long enough, please re-enter", Toast.LENGTH_SHORT).show();
                } else if (password.length()<8) {
                    Toast.makeText(Signupactivity.this, "Password not long enough, please re-enter", Toast.LENGTH_SHORT).show();
                } else if (password2 == null) {
                    Toast.makeText(Signupactivity.this, "ConfirmPassword cannot be empty, please re-enter", Toast.LENGTH_SHORT).show();
                } else if (!password2.equals(password)) {
                    Toast.makeText(Signupactivity.this, "ConfirmPassword is not equal to Password", Toast.LENGTH_SHORT).show();
                } else if (fullname.length()<4) {
                    Toast.makeText(Signupactivity.this, "Full name not long enough, please re-enter", Toast.LENGTH_SHORT).show();
                }
                else {
                    db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("username", username);
                    values.put("password", password);
                    values.put("fullname", fullname);
                    long result = db.insert("user", null, values);
                    db.close();
                    dbHelper.close();
                    if (result > 0) {
                        Toast.makeText(Signupactivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Signupactivity.this, MainActivity.class);   //Intent intent=new Intent(MainActivity.this,JumpToActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    public void findid(){
        Username = (EditText) findViewById(R.id.Username);
        Fullname = (EditText) findViewById(R.id.Fullname);
        Password = (EditText) findViewById(R.id.Password);
        ConfirmPassword = (EditText) findViewById(R.id.Password2);
        Create = (Button) findViewById(R.id.Create);
    }
}


