package com.polymorph.hildajoubert.helena20;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Percy on 2016/09/08.
 */

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<DummyQuestion> questions;
    Activity activity;

    public QuestionRecyclerAdapter(List<DummyQuestion> questions, Activity activity) {
        this.questions = questions;
        this.activity = activity;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_question, parent, false);
        SettingsUploadDocumentRowHolder rowHolder = new SettingsUploadDocumentRowHolder(view);
        return rowHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SettingsUploadDocumentRowHolder uploadRow = (SettingsUploadDocumentRowHolder) holder;
        uploadRow.setQuestion(questions.get(position).getQuestion());
        uploadRow.setDate(questions.get(position).getDate());
        uploadRow.setAuthor(questions.get(position).getAuthor());
        //setStuff
    }
    @Override
    public int getItemCount() {
        return (null != questions ? questions.size() : 0);
    }



    class SettingsUploadDocumentRowHolder extends RecyclerView.ViewHolder {

        TextView question;
        TextView date;
        TextView author;

        public SettingsUploadDocumentRowHolder(View view) {
            super(view);
            setupViews(view);
        }

        private void setupViews(View view) {
            question = (TextView) view.findViewById(R.id.textView_list_question);
            date = (TextView) view.findViewById(R.id.textView_list_date);
            author = (TextView) view.findViewById(R.id.textView_list_author);
        }

        public void setQuestion(String question) {
            this.question.setText(question);
        }

        public void setDate(String date) {
            this.date.setText(date);
        }

        public void setAuthor(String author) {
            this.author.setText(author);
        }

    }

}
