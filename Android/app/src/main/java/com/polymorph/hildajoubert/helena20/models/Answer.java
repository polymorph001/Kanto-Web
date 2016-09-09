package com.polymorph.hildajoubert.helena20.models;

/**
 * Created by Admin on 2016/09/08.
 */
public class Answer {

    private String answer;
    private String answerId;
    private String questionId;
    private String userId;

    public Answer() {

    }

    public Answer(String answer, String answerId, String questionId, String userId) {
        this.answer = answer;
    }

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
}
