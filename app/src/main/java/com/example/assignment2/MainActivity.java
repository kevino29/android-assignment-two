package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private String name;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent("QuizActivity");
                Bundle extras = new Bundle();
                extras.putString("NAME", name);
                i.putExtras(extras);
                startActivityForResult(i, 1);
            }
        });
    }
}