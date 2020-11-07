package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvScore = findViewById(R.id.tvScore);
        Button btnTryAgain = findViewById(R.id.btnTryAgain);
        String name = null;
        int score = 0;

        Bundle extras = getIntent().getExtras();

        try {
            if (extras != null) {
                name = extras.getString("NAME");
                score = extras.getInt("SCORE");
            }
        } catch (Exception e) {
            System.out.println("An unexpected error has occurred.");
            Log.e("Error", "General Error");
            e.printStackTrace();
        }

        tvName.setText(name);
        tvScore.setText(String.valueOf(score + " / 10"));

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultsActivity.this, MainActivity.class));
            }
        });
    }
}