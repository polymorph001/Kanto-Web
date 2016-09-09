package com.polymorph.hildajoubert.helena20.ui.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.polymorph.hildajoubert.helena20.models.Question;
import com.polymorph.hildajoubert.helena20.R;
import com.polymorph.hildajoubert.helena20.models.QuestionRowItem;

import java.util.List;

/**
 * Created by Percy on 2016/09/08.
 */

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<QuestionRowItem> questions;

    public QuestionRecyclerAdapter(List<QuestionRowItem> questions) {
        this.questions = questions;
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
        uploadRow.setQuestion(questions.get(position));
    }
    @Override
    public int getItemCount() {
        return (null != questions ? questions.size() : 0);
    }

    class SettingsUploadDocumentRowHolder extends RecyclerView.ViewHolder {

        private TextView question;
        private TextView date;
        private TextView author;
        private TextView numberOfAnswers;
        private ImageView answeredImage;

        private QuestionRowItem questionRowItem;

        public SettingsUploadDocumentRowHolder(View view) {
            super(view);
            setupViews(view);
        }

        private void setupViews(View view) {
            question = (TextView) view.findViewById(R.id.textView_list_question);
            date = (TextView) view.findViewById(R.id.textView_list_date);
            author = (TextView) view.findViewById(R.id.textView_list_author);
            numberOfAnswers = (TextView) view.findViewById(R.id.textView_list_numberAnswers);
            answeredImage = (ImageView) view.findViewById(R.id.imageView_list_answered);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("questionId",questionRowItem.getQuestion().getId());
                }
            });
        }

        public void setQuestion(QuestionRowItem questionItem) {
            this.questionRowItem = questionItem;

            this.question.setText(questionItem.getQuestion().getQuestion());
            this.date.setText(questionItem.getQuestion().getTimestamp());
            this.author.setText(questionItem.getQuestion().getCreatedBy());
            this.numberOfAnswers.setText(""+questionItem.getAnswerCount());
            shouldDisplayQuestionAnswered(questionItem.isAnsweredByMe());
        }



        private void shouldDisplayQuestionAnswered(Boolean shouldDisplay) {
            if (shouldDisplay) {
                answeredImage.setImageResource(R.drawable.com_facebook_button_like_icon_selected);
            }else{
                answeredImage.setImageResource(R.drawable.com_facebook_auth_dialog_cancel_background);
            }
        }
    }

}
