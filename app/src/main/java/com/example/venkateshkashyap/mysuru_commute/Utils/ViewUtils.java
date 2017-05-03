package com.example.venkateshkashyap.mysuru_commute.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.support.design.widget.AppBarLayout;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Srivatsa
 */
public class ViewUtils {
    private static final String TAG = ViewUtils.class.getSimpleName();


    public static void enableTheViews(View... views) {
        for (View v : views)
            if (v != null)
                v.setEnabled(true);
    }

    public static void hideTheViews(View... views) {
        for (View v : views)
            if (v != null)
                v.setVisibility(View.GONE);
    }

    public static void makeViewsInvisible(View... views) {
        for (View v : views)
            if (v != null)
                v.setVisibility(View.INVISIBLE);
    }

    public static void showTheViews(View... views) {
        for (View v : views)
            if (v != null)
                v.setVisibility(View.VISIBLE);
    }

    public static void disableTheViews(View... views) {
        for (View v : views) {
            if (v != null)
                v.setEnabled(false);
        }

    }

    public static void deselectTheViews(View... views) {
        for (View v : views) {
            if (v != null)
                v.setSelected(false);
        }
    }

    public static void selectTheViews(View... views) {
        for (View v : views) {
            if (v != null)
                v.setSelected(true);
        }
    }

    public static void setTagToViews(View... views) {
        for (View v : views) {
            if (v != null)
                v.setTag(((TextView) v).getText());
        }
    }

    public static void animateAppBar(final AppBarLayout appBar) {
        new CountDownTimer(300, 1) {
            public void onTick(long millisUntilFinished) {
                appBar.setTranslationY(-appBar.getHeight());
            }

            public void onFinish() {
                appBar.animate()
                        .translationY(0)
                        .setDuration(500).start();
            }
        }.start();
    }


    public interface ToolbarManipulation {
        void collapseToolbar();

        void expandToolbar();

        void setTitle(String s);
    }



    public static int getThemeColors(Resources.Theme theme, int attr){
        TypedValue typedvalueattr = new TypedValue();
        theme.resolveAttribute(attr, typedvalueattr, true);
        return typedvalueattr.resourceId;
    }

}
