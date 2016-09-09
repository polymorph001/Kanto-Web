package com.polymorph.hildajoubert.helena20.model;

public class Question {

    String question;
    String timestamp;
    String createdBy;

    public Question() {

    }

    public Question(String question, String timestamp, String createdBy) {
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
}