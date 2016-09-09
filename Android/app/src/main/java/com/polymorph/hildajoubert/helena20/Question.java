package com.polymorph.hildajoubert.helena20;

/**
 * Created by Percy on 2016/09/08.
 */

public class Question {


    private String question;
    private String timestamp;
    private String createdBy;
    private String id;


    public Question() {

    }

    public Question (String id, String question, String timestamp, String createdBy) {
        this.id = id;
        this.question = question;
        this.timestamp = timestamp;
        this.createdBy = createdBy;
    }

    public String getQuestion() {
        return question;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getId() {
        return id;
    }
}
