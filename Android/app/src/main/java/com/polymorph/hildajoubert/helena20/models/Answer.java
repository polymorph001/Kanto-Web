package com.polymorph.hildajoubert.helena20.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 2016/09/08.
 */
public class Answer implements Parcelable{

    private String answer;
    private String answerId;
    private String questionId;
    private String userId;
    private String email;

    public Answer() {

    }

    public Answer(String answer, String answerId, String questionId, String userId, String email) {
        this.answer = answer;
        this.answerId = answerId;
        this.questionId = questionId;
        this.userId = userId;
        this.email = email;
    }

    protected Answer(Parcel in) {
        answer = in.readString();
        answerId = in.readString();
        questionId = in.readString();
        userId = in.readString();
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    public String getAnswer() {
        return answer;
    }

    public String getAnswerId() {
        return answerId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() { return email;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(answer);
        parcel.writeString(answerId);
        parcel.writeString(questionId);
        parcel.writeString(userId);
    }
}
