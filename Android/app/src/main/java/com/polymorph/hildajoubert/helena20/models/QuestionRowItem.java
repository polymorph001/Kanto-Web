package com.polymorph.hildajoubert.helena20.models;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * Created by Percy on 2016/09/09.
 */

public class QuestionRowItem {

    private Question question;
    private List<Answer> answers;

    public QuestionRowItem(Question question) {
        this.question = question;
    }

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
}
