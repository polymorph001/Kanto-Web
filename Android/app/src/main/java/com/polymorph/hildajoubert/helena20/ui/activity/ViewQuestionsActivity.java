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
import com.polymorph.hildajoubert.helena20.Question;
import com.polymorph.hildajoubert.helena20.QuestionRecyclerAdapter;
import com.polymorph.hildajoubert.helena20.R;

import java.util.ArrayList;
import java.util.List;

public class ViewQuestionsActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    RecyclerView questionsList;
    List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_questions);

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
                                questions.add(postSnapshot.getValue(Question.class));
                                Log.w("Questions", "added question");
                            }
                        }
                        updateData();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("Question List", "get Question:onCancelled", databaseError.toException());
                    }
                });
    }

    private void updateData(){
        QuestionRecyclerAdapter adapter = new QuestionRecyclerAdapter(questions);
        questionsList.setAdapter(adapter);
    }


}
