package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.*;
import android.os.*;
import android.util.*;
import android.view.View;
import android.widget.*;

import java.io.*;
import java.util.*;

public class QuizActivity extends AppCompatActivity {
    private ArrayList<Button> options = new ArrayList<>();
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> answers = new ArrayList<>();
    private String answer;
    private TextView tvQuestionNum;
    private TextView tvQuestion;
    private ProgressBar pbProgress;
    private int progress;
    private InputStream is;
    private BufferedReader br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        init();

        tvQuestionNum = findViewById(R.id.tvQuestionNum);
        tvQuestion = findViewById(R.id.tvQuestion);
        pbProgress = findViewById(R.id.pbProgress);

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
        showQuiz();
    }

    private void init() {
        answer = null;
        progress = 0;
        getQuizData();
    }

    private void getQuizData() {
        String input = "";
        String[] inputs = new String[2];

        try {
            is = getResources().openRawResource(R.raw.quiz_data);
            br = new BufferedReader(new InputStreamReader(is));

            while ((input = br.readLine()) != null) {
                inputs = input.split("@");
                questions.add(inputs[0]);
                answers.add(inputs[1]);
            }
        }
        catch (IOException e) {
            System.out.println("An IO error has occurred.");
            Log.e("Error", "File IO");
            e.printStackTrace();
        }
        catch (Exception e) {
            System.out.println("An unexpected error has occurred.");
            Log.e("Error", "General Error");
            e.printStackTrace();
        }
    }

    private void showQuiz() {

    }

    private class OptionButtonClicked implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            progress++;
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
            pbProgress.setProgress(progress);
        }
    }
}