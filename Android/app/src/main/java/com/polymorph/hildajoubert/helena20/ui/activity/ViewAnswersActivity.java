package com.polymorph.hildajoubert.helena20.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.polymorph.hildajoubert.helena20.R;
import com.polymorph.hildajoubert.helena20.StaticHolder;
import com.polymorph.hildajoubert.helena20.models.QuestionRowItem;
import com.polymorph.hildajoubert.helena20.ui.adapters.AnswerRecyclerAdapter;

public class ViewAnswersActivity extends AppCompatActivity {

    public final static String QUESTION_ITEM_KEY = "questionItemKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_answers);

        setupViews();
    }


    public void setupViews() {
        TextView question = (TextView) findViewById(R.id.textView_viewAnswers_question);

        RecyclerView answersRecycler = (RecyclerView) findViewById(R.id.recyclerView_viewAnswers_listOfAnswers);
        answersRecycler.setLayoutManager(new LinearLayoutManager(this));

        QuestionRowItem item = StaticHolder.questionRowItem;
        AnswerRecyclerAdapter adapter = new AnswerRecyclerAdapter(item.getAnswers());

        answersRecycler.setAdapter(adapter);
        
        question.setText(item.getQuestion().getQuestion());

    }
}
