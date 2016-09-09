package com.polymorph.hildajoubert.helena20.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.polymorph.hildajoubert.helena20.R;
import com.polymorph.hildajoubert.helena20.StaticHolder;
import com.polymorph.hildajoubert.helena20.models.QuestionRowItem;
import com.polymorph.hildajoubert.helena20.ui.activity.AnswerQuestionsActivity;
import com.polymorph.hildajoubert.helena20.ui.activity.ViewAnswersActivity;

import java.util.List;

/**
 * Created by Percy on 2016/09/08.
 */

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<QuestionRowItem> questions;
    private Context context;

    public QuestionRecyclerAdapter(List<QuestionRowItem> questions, Context context) {
        this.questions = questions;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_question, parent, false);
        QuestionRowHolder rowHolder = new QuestionRowHolder(view, context);
        return rowHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        QuestionRowHolder uploadRow = (QuestionRowHolder) holder;
        uploadRow.setQuestion(questions.get(position));
    }
    @Override
    public int getItemCount() {
        return (null != questions ? questions.size() : 0);
    }

    class QuestionRowHolder extends RecyclerView.ViewHolder {

        private TextView question;
        private TextView date;
        private TextView author;
        private TextView numberOfAnswers;
        private ImageView answeredImage;

        private QuestionRowItem questionRowItem;
        private Context context;
        private View view;

        public QuestionRowHolder(View view, Context context) {
            super(view);
            this.context = context;
            this.view = view;
            setupViews();
        }

        private void setupViews() {
            question = (TextView) view.findViewById(R.id.textView_list_question);
            date = (TextView) view.findViewById(R.id.textView_list_date);
            author = (TextView) view.findViewById(R.id.textView_list_author);
            numberOfAnswers = (TextView) view.findViewById(R.id.textView_list_numberAnswers);
            answeredImage = (ImageView) view.findViewById(R.id.imageView_list_answered);

        }

        public void setQuestion(final QuestionRowItem questionItem) {
            this.questionRowItem = questionItem;

            this.question.setText(questionItem.getQuestion().getQuestion());
            this.date.setText(questionItem.getQuestion().getTimestamp());
            this.author.setText(questionItem.getQuestion().getCreatedBy());
            this.numberOfAnswers.setText(""+questionItem.getAnswerCount());
            shouldDisplayQuestionAnswered(questionItem.isAnsweredByMe());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(questionItem.isAnsweredByMe()) {
                        Intent intent = new Intent(context, ViewAnswersActivity.class);
                        StaticHolder.questionRowItem = questionItem;
                        context.startActivity(intent);
                    } else {
                        Intent intent = new Intent(context, AnswerQuestionsActivity.class);
                        intent.putExtra("questionId", questionRowItem.getQuestion().getId());
                        context.startActivity(intent);
                    }
                }
            });
        }



        private void shouldDisplayQuestionAnswered(Boolean shouldDisplay) {
            if (shouldDisplay) {
                answeredImage.setImageDrawable(new IconDrawable(context,FontAwesomeIcons.fa_check_circle));
            }else{
                answeredImage.setImageDrawable(new IconDrawable(context,FontAwesomeIcons.fa_times_circle));
            }
        }
    }

}
