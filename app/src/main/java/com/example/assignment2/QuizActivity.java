package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;

public class QuizActivity extends AppCompatActivity {
    private HashMap<String, String> quiz;
    private ArrayList<Button> options;
    private ArrayList<String> answerOptions;
    private ArrayList<String> answers;
    private String correctAnswer;
    private TextView tvQuestionNum;
    private TextView tvQuestion;
    private ProgressBar pbProgress;
    private int progress;
    private int questionNumber;
    private int score;

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
        startQuiz();
    }

    private void init() {
        quiz = new HashMap<>();
        options = new ArrayList<>();
        answerOptions = new ArrayList<>();
        answers = new ArrayList<>();
        correctAnswer = null;
        progress = 0;
        questionNumber = 0;
        score = 0;
        getQuizData();
    }

    private void getQuizData() {
        InputStream is;
        BufferedReader br;
        String input;
        String[] inputs;

        try {
            is = getResources().openRawResource(R.raw.quiz_data);
            br = new BufferedReader(new InputStreamReader(is));

            while ((input = br.readLine()) != null) {
                inputs = input.split("@");
                answers.add(inputs[1]);
                quiz.put(inputs[1], inputs[0]);
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

    private void startQuiz() {
        Collections.shuffle(answers);
        questionNumber = 0;

        showQuiz(questionNumber);
    }

    @SuppressLint("DefaultLocale")
    private void showQuiz(int questionNum) {
        correctAnswer = answers.get(questionNum);
        if (answerOptions.size() > 0)
            answerOptions.clear(); //resets the options

        tvQuestionNum.setText(String.format("Question %d", questionNum+1));
        tvQuestion.setText(quiz.get(correctAnswer));
        quiz.remove(correctAnswer); //removes the question from the hash map

        answerOptions.add(correctAnswer);
        while (answerOptions.size() != 4) {
            String temp = answers.get(getRandomNum(0, quiz.size()-1));

            if (!answerOptions.contains(temp)) //checks for any duplication in the options
                answerOptions.add(temp);
        }
        Collections.shuffle(answerOptions);

        int c = 0;
        for (Button button : options) {
            button.setText(answerOptions.get(c++));
        }
    }

    private void finishQuiz() {
        TableLayout tlOptions = findViewById(R.id.tlOptions);
        TableLayout tlContinue = findViewById(R.id.tlContinue);
        TableRow trQuestion = findViewById(R.id.trQuestion);
        Button btnContinue = findViewById(R.id.btnContinue);

        tlOptions.setVisibility(View.GONE);
        trQuestion.setVisibility(View.GONE);
        tlContinue.setVisibility(View.VISIBLE);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("ResultsActivity");
                Bundle extras = getIntent().getExtras();

                try {
                    if (extras != null) {
                        extras.putInt("SCORE", score); //adds the final score to the bundle
                        intent.putExtras(extras);
                    }
                } catch (Exception e) {
                    System.out.println("An unexpected error has occurred.");
                    Log.e("Error", "General Error");
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });
    }

    private int getRandomNum(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    private class OptionButtonClicked implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String inputAnswer = ((Button)view).getText().toString(); //gets the answer from the button

            if (inputAnswer.equals(correctAnswer)) {
                Toast.makeText(getApplicationContext(), "That is the correct answer!", Toast.LENGTH_SHORT).show();
                score++;
            }
            else
                Toast.makeText(getApplicationContext(), "That is the wrong answer!", Toast.LENGTH_SHORT).show();

            pbProgress.setProgress(++progress);

            if (quiz.size() > 0) //checks if the quiz is not empty
                showQuiz(++questionNumber);
            else
                finishQuiz();
        }
    }
}