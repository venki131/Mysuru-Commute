package com.example.venkateshkashyap.mysuru_commute.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.venkateshkashyap.mysuru_commute.R;



/**
 * Created by Srivatsa M.
 */
public class DialogUtils {

    private static ProgressDialog sProgressDialog;


    /**
     * Creates and displays Toast with given message.
     *
     * @param msg
     * @param ctx
     */
    public static void showToast(String msg, Context ctx) {
        //TODO Add flag to enable or disable toast
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    public static void displayProgressDialog(Context context) {
        displayProgressDialog(context, null);

    }



    /**
     * Creates and shows progress dialog and sets your OnCancelListener
     *
     * @param context
     * @param listener
     */
    public static void displayProgressDialog(Context context,
                                             OnCancelListener listener) {
        displayProgressDialog(context, listener,
                context.getString(R.string.loading));
    }

    /**
     * Creates and shows progress dialog with given message and sets your
     * OnCancelListener
     *
     * @param context
     * @param listener
     * @param msg
     */
    public static void displayProgressDialog(Context context,
                                             OnCancelListener listener, String msg) {
        if (sProgressDialog != null && sProgressDialog.isShowing())
            return;

        if (context != null) {
            sProgressDialog = new ProgressDialog(context);
            sProgressDialog.setMessage(msg);
//            sProgressDialog.setTitle(R.string.app_n.ame);
            sProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            sProgressDialog.setCancelable(false);
            sProgressDialog.setOnCancelListener(listener);
            try {
                sProgressDialog.show();
            } catch (WindowManager.BadTokenException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Dismisses progress dialog.
     */
    public static void hideProgressDialog() {
        if (sProgressDialog != null && sProgressDialog.isShowing()) {
            try {
                sProgressDialog.dismiss();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            sProgressDialog = null;
        }
    }



    public static void showNetworkAlertDialog(Context mContext){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setMessage("Please check you");
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        alertDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                alertDialog.dismiss();
            }
        });

    }



}
