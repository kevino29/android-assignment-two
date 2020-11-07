package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.view.*;
import android.widget.*;
import android.content.Intent;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private String name;
    private Button btnStart;
    private EditText ptName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        ptName = findViewById(R.id.ptName);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = ptName.getText().toString();

                if (!name.equals("")) {
                    Intent i = new Intent("QuizActivity");
                    Bundle extras = new Bundle();

                    extras.putString("NAME", name);
                    i.putExtras(extras);

//                    startActivityForResult(i, 1);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter a name to continue", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}