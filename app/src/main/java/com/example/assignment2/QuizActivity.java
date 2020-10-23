package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.*;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private ArrayList<Button> options = new ArrayList<>();
    private TextView tvQuestionNum;
    private TextView tvQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestionNum = findViewById(R.id.tvQuestionNum);
        tvQuestion = findViewById(R.id.tvQuestion);

        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    options.add((Button)findViewById(R.id.btnOption1));
                    break;
                case 1:
                    options.add((Button)findViewById(R.id.btnOption2));
                    break;
                case 2:
                    options.add((Button)findViewById(R.id.btnOption3));
                    break;
                case 3:
                    options.add((Button)findViewById(R.id.btnOption4));
                    break;
            }
            options.get(i).setOnClickListener(new OptionButtonClicked());
        }
    }

    private class OptionButtonClicked implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnOption1:
                    String s = null;
                    break;
                case R.id.btnOption2:
                    break;
                case R.id.btnOption3:
                    String d = "something";
                    break;
                case R.id.btnOption4:
                    String a = "nothing";
                    break;
            }
        }
    }
}