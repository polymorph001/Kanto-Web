package com.polymorph.hildajoubert.helena20.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 2016/09/08.
 */
public class Question implements Parcelable{

    private String createdBy;
    private String question;
    private String timestamp;
    private String id;

    public Question() {
        // Default
    }

    protected Question(Parcel in) {
        createdBy = in.readString();
        question = in.readString();
        timestamp = in.readString();
        id = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getCreatedBy() {
        return createdBy;
    }

    public String getQuestion() {
        return question;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(createdBy);
        parcel.writeString(question);
        parcel.writeString(timestamp);
        parcel.writeString(id);
    }
}
