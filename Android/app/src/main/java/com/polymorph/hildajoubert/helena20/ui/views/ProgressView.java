package com.polymorph.hildajoubert.helena20.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.polymorph.hildajoubert.helena20.R;

public class ProgressView extends FrameLayout {

    private Context context;

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        final View view = LayoutInflater
                .from(context)
                .inflate(R.layout.layout_view_progress, this);

    }

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context) {
        this(context, null);
    }
}
