package com.polymorph.hildajoubert.helena20.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Percy on 2016/09/09.
 */

public class QuestionRowItem implements Parcelable{

    private Question question;
    private List<Answer> answers;

    public QuestionRowItem(Question question) {
        this.question = question;
    }

    protected QuestionRowItem(Parcel in) {
    }

    public static final Creator<QuestionRowItem> CREATOR = new Creator<QuestionRowItem>() {
        @Override
        public QuestionRowItem createFromParcel(Parcel in) {
            return new QuestionRowItem(in);
        }

        @Override
        public QuestionRowItem[] newArray(int size) {
            return new QuestionRowItem[size];
        }
    };

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public int getAnswerCount(){
        if (answers == null) {
            return 0;
        }
        return answers.size();
    }

    public boolean isAnsweredByMe(){

        if (answers == null){
            return false;
        }

        for (Answer answer: answers) {
            if (answer.getUserId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
