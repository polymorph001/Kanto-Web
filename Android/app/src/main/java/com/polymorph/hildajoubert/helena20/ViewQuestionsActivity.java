package com.polymorph.hildajoubert.helena20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewQuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_questions);

        setupViews();
    }


    private void setupViews() {
        RecyclerView questionsList = (RecyclerView) findViewById(R.id.recyclerView_viewQuestions_listOfQuestions);
        questionsList.setLayoutManager(new LinearLayoutManager(this));
        List<DummyQuestion> questions = new ArrayList<>();
        questions.add(new DummyQuestion("What are you working on","10-08-2016","Wim"));
        questions.add(new DummyQuestion("Where did you work before","09-08-2016","Wim"));
        questions.add(new DummyQuestion("Do you enjoy the team days","08-08-2016","Wim"));
        QuestionRecyclerAdapter adapter = new QuestionRecyclerAdapter(questions,this);
        questionsList.setAdapter(adapter);

    }
}
