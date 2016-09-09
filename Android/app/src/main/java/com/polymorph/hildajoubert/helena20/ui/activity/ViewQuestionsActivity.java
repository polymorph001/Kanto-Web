package com.polymorph.hildajoubert.helena20.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.polymorph.hildajoubert.helena20.models.Answer;
import com.polymorph.hildajoubert.helena20.models.Question;
import com.polymorph.hildajoubert.helena20.models.QuestionRowItem;
import com.polymorph.hildajoubert.helena20.ui.adapters.QuestionRecyclerAdapter;
import com.polymorph.hildajoubert.helena20.R;

import java.util.ArrayList;

public class ViewQuestionsActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    RecyclerView questionsList;
    ArrayList<QuestionRowItem> questionRowItems;
    ArrayList<Question> questions;
    ArrayList<Answer> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_questions);
        questionRowItems = new ArrayList<>();
        questions = new ArrayList<>();
        answers = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        setupViews();
    }


    private void setupViews() {
        questionsList = (RecyclerView) findViewById(R.id.recyclerView_viewQuestions_listOfQuestions);
        questionsList.setLayoutManager(new LinearLayoutManager(this));
        questions = new ArrayList<>();

        fetchQuestionsFromFireBase();
    }

    private void fetchQuestionsFromFireBase() {
        mDatabase.child("kyc").child("question").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                questionRowItems.add(new QuestionRowItem(postSnapshot.getValue(Question.class)));
                                Log.w("Questions", "added question");
                            }
                            fetchAnswersForQuestion(questionRowItems, 0);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("Question List", "get Question:onCancelled", databaseError.toException());
                    }
                });
    }

    private void fetchAnswersForQuestion(final ArrayList<QuestionRowItem> questions, final int index) {

        Log.w("Questions", "fetching answers");
        mDatabase.child("kyc").child("question-answer").child(questions.get(index).getQuestion().getId()).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.w("Questions", "On data changed");
                        if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                            Log.w("Questions", "not null");
                            answers = new ArrayList<Answer>();
                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                answers.add(postSnapshot.getValue(Answer.class));
                                Log.w("Questions", "added answer");
                            }
                            questionRowItems.get(index).setAnswers(answers);

                            if (index < questions.size() - 1) {
                                fetchAnswersForQuestion(questions, index + 1);
                            }else{
                                updateData();
                            }
                        }else{
                            if (index < questions.size() - 1) {
                                fetchAnswersForQuestion(questions, index + 1);
                            }else{
                                updateData();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("Question List", "get answersForQuestion:onCancelled", databaseError.toException());
                    }
                });
    }

    private void updateData(){
        QuestionRecyclerAdapter adapter = new QuestionRecyclerAdapter(questionRowItems);
        questionsList.setAdapter(adapter);
    }


}
