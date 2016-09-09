package com.polymorph.hildajoubert.helena20.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.polymorph.hildajoubert.helena20.R;
import com.polymorph.hildajoubert.helena20.ui.activity.AnswerQuestionsActivity;
import com.polymorph.hildajoubert.helena20.ui.views.ProgressView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class KnowYourCompanyLauncherActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.vProgress)
    ProgressView vProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_your_company_launcher);

        ButterKnife.bind(this);

        setupViews();
    }

    @Override
    public void onStart() {
        super.onStart();

        // Example Progress
//        vProgress.setVisibility(View.VISIBLE);
//        Subscription sub = Observable.just(1)
//                .delay(10, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        vProgress.setVisibility(View.GONE);
//                    }
//                });
//        subs.add(sub);

    }

    private void setupViews(){

        Button b1 = (Button) findViewById(R.id.button_launcher_QuestionsToAnswer);
        b1.setOnClickListener(this);
        Button b2 = (Button) findViewById(R.id.button_launcher_ListOfQuestions);
        b2.setOnClickListener(this);
        Button b3 = (Button) findViewById(R.id.button_launcher_UserProfile);
        b3.setOnClickListener(this);
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
            case R.id.button_launcher_UserProfile:
                startActivity(new Intent(this, ProfileActivity.class));

        }
    }
}
