package com.polymorph.hildajoubert.helena20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AnswerQuestionsActivity extends AppCompatActivity {

    private TextView questionTextView;
    private TextView answerEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_questions);
        initViews();
    }

    private void initViews() {
        questionTextView = (TextView) findViewById(R.id.tv_answer_question_question);
        answerEditText = (EditText) findViewById(R.id.et_answer_question_answer);
        submitButton = (Button) findViewById(R.id.bt_answer_question_submit_answer);
    }
}
