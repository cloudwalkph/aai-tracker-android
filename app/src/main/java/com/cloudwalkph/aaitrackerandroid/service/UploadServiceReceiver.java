package com.cloudwalkph.aaitrackerandroid.service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.cloudwalkph.aaitrackerandroid.R;

/**
 * Created by trick.sunga on 28/11/2016.
 */

public class UploadServiceReceiver extends BroadcastReceiver {
    public static final String ACTION_RESP =
            "com.cloudwalkph.aaitrackerandroid.action.MESSAGE_PROCESSED";
    private Snackbar snackbar;

    public UploadServiceReceiver(Activity activity) {
        snackbar = Snackbar.make(activity.findViewById(R.id.coordinator_layout), "", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(ContextCompat.getColor(activity, R.color.white));
        View snackBarView = snackbar.getView();
        // text color
        TextView tv = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(ContextCompat.getColor(activity, R.color.white));
        // bg color
        snackBarView.setBackgroundColor(ContextCompat.getColor(activity, R.color.aaiOrange));
        // move to top
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) snackBarView.getLayoutParams();
        params.gravity = Gravity.TOP;
        snackBarView.setLayoutParams(params);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(UploadService.PARAM_OUT_MSG);
        snackbar.setText(message);
        if(!snackbar.isShown()) snackbar.show();
    }
}
