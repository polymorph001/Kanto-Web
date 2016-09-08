package com.polymorph.hildajoubert.helena20;

/**
 * Created by Percy on 2016/09/08.
 */

public class DummyQuestion {

    String question;
    String date;
    String author;

    public DummyQuestion (String question, String date, String author) {
        this.question = question;
        this.date = date;
        this.author = author;
    }

    public String getQuestion() {
        return question;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }
}
