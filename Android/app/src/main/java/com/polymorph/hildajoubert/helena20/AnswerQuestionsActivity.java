package com.polymorph.hildajoubert.helena20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.polymorph.hildajoubert.helena20.models.Answer;
import com.polymorph.hildajoubert.helena20.models.Question;

public class AnswerQuestionsActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "AnswerQuestionsActivity";
    private TextView questionTextView;
    private TextView answerEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_questions);
        initViews();
        getQuestion();
    }

    private void initViews() {
        questionTextView = (TextView) findViewById(R.id.tv_answer_question_question);
        answerEditText = (EditText) findViewById(R.id.et_answer_question_answer);
        submitButton = (Button) findViewById(R.id.bt_answer_question_submit_answer);
        submitButton.setOnClickListener(this);
    }

    private void getQuestion() {

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("kyc").child("question").child("test-question00").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Question question = dataSnapshot.getValue(Question.class);
                        questionTextView.setText(question.getQuestion());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

    }

    private void submitQuestion() {

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        Answer answer = new Answer(answerEditText.getText().toString(), "answerID", "questionID", "userID");

        mDatabase.child("kyc").child("test-user00").child("test-answer00").setValue(answer);
    }

    @Override
    public void onClick(View view) {
        submitQuestion();
    }
}
