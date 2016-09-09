package com.polymorph.hildajoubert.helena20.util.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class AppPreferences {
    private static final String TAG = AppPreferences.class.getSimpleName();

    /**
     * The Constant PREFS_NAMESPACE.
     */
    public static final String PREFS_NAMESPACE = "com.polymorph.hildajoubert.helena20.prefs.app";

    private SharedPreferences prefs;
    private Context context;

    /**
     * Constructor
     *
     * @param context
     */
    public AppPreferences(@NonNull final Context context) {
        prefs = context.getSharedPreferences(PREFS_NAMESPACE,
                Context.MODE_PRIVATE);
        this.context = context;
    }

}
