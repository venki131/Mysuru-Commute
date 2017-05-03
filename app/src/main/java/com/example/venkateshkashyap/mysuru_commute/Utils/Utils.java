package com.example.venkateshkashyap.mysuru_commute.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.venkateshkashyap.mysuru_commute.R;
import com.example.venkateshkashyap.mysuru_commute.constants.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Srivatsa
 */
public class Utils {

    /**
     * @author Srivatsa
     *
     */
    public static String DeviceResolution(Context context){
        int density= context.getResources().getDisplayMetrics().densityDpi;
        String resolution="";
        switch(density)
        {
            case DisplayMetrics.DENSITY_LOW:
                resolution= Constants.LDPI;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                resolution=Constants.MDPI;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                resolution=Constants.HDPI;
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                resolution=Constants.XHDPI;
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                resolution=Constants.XXHDPI;
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                resolution=Constants.XXXHDPI;
                break;
            default:
                resolution = Constants.HDPI;


        }
        return resolution;
    }

    public static void setErrorView(View mainView, View emptyView, Context context, Drawable errorDrawable, String errorTitle, String errorSubTitle,String tryAgainText, boolean shouldHideTryAgain){
        ViewUtils.hideTheViews(mainView);
        ViewUtils.showTheViews(emptyView);

        TextView mTextViewTitle = (TextView) emptyView.findViewById(R.id.text_title);
        TextView mTextViewSubText = (TextView) emptyView.findViewById(R.id.text_sub_title);
        ImageView mImageView = (ImageView) emptyView.findViewById(R.id.img_error_image);

        TextView mTextViewTryAgain = (TextView) emptyView.findViewById(R.id.text_try_again);

        mImageView.setImageDrawable(errorDrawable);
        mTextViewTitle.setText(errorTitle);
        mTextViewSubText.setText(errorSubTitle);
        mTextViewTryAgain.setText(tryAgainText);

        if(shouldHideTryAgain) {
            ViewUtils.hideTheViews(mTextViewTryAgain);
        }
    }


    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    public static void hideKeyboard(Activity activity) {
        // Check if no view has focus:
         View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

}
}
