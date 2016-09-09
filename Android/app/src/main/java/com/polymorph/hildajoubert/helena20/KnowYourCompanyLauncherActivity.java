package com.polymorph.hildajoubert.helena20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class KnowYourCompanyLauncherActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_your_company_launcher);

        setupViews();
    }

    private void setupViews(){

        Button b1 = (Button) findViewById(R.id.button_launcher_QuestionsToAnswer);
        b1.setOnClickListener(this);
        Button b2 = (Button) findViewById(R.id.button_launcher_ListOfQuestions);
        b2.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.button_launcher_QuestionsToAnswer:
                startActivity(new Intent(this, AnswerQuestionsActivity.class));
                break;
            case R.id.button_launcher_ListOfQuestions:
                Intent launchListIntent = new Intent(this, ViewQuestionsActivity.class);
                startActivity(launchListIntent);
                break;

        }
    }
}
