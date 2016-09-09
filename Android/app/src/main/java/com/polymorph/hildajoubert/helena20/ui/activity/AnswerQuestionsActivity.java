package com.polymorph.hildajoubert.helena20.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.polymorph.hildajoubert.helena20.R;
import com.polymorph.hildajoubert.helena20.models.Answer;
import com.polymorph.hildajoubert.helena20.models.Question;

public class AnswerQuestionsActivity extends AppCompatActivity implements View.OnClickListener {

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

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

//        mDatabase.child("kyc").child("question-answer").child(questionId).addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        dialog.dismiss();
//                        if (dataSnapshot != null && dataSnapshot.getValue() != null) {
//            pointsId = rootDataReference.child("points").child(userId).push().getKey();*

        final String questionId = "test-question01"; // TODO
        String userAnswer = answerEditText.getText().toString();
        String answerId = mDatabase.child("question-answer").child(questionId).push().getKey();
        String userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Answer answer = new Answer(userAnswer, answerId, questionId, userUID);

        mDatabase.child("kyc").child("question-answer").child(questionId).child(answerId).setValue(answer);

//                        } else {
//                            Toast.makeText(AnswerQuestionsActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        dialog.dismiss();
//                        Toast.makeText(AnswerQuestionsActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
//                    }
//                });
    }

    @Override
    public void onClick(View view) {
        submitQuestion();
    }
}
