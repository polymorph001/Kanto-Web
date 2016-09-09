package com.polymorph.hildajoubert.helena20.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.polymorph.hildajoubert.helena20.R;
import com.polymorph.hildajoubert.helena20.models.Answer;
import com.polymorph.hildajoubert.helena20.models.Question;
import com.polymorph.hildajoubert.helena20.models.QuestionRowItem;

import java.util.List;

/**
 * Created by Percy on 2016/09/08.
 */

public class AnswerRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Answer> answers;

    public AnswerRecyclerAdapter(List<Answer> answers) {
        this.answers = answers;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_answer, parent, false);
        AnswerRowHolder rowHolder = new AnswerRowHolder(view);
        return rowHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        AnswerRowHolder uploadRow = (AnswerRowHolder) holder;
        uploadRow.setAnswer(answers.get(position).getAnswer());
        uploadRow.setAuthor(answers.get(position).getEmail());
    }
    @Override
    public int getItemCount() {
        return (null != answers ? answers.size() : 0);
    }

    class AnswerRowHolder extends RecyclerView.ViewHolder {

        private TextView answer;
        private TextView author;

        public AnswerRowHolder(View view) {
            super(view);
            setupViews(view);
        }

        private void setupViews(View view) {
            answer = (TextView) view.findViewById(R.id.textView_listItem_answer);
            author = (TextView) view.findViewById(R.id.textView_listItem_author);
        }

        public void setAnswer(String answer) {
            this.answer.setText(answer);
        }

        public void setAuthor(String author) {
            this.author.setText(author);
        }

    }

}
