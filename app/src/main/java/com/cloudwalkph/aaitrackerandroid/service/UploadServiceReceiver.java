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
        View snackBarView = snackbar.getView();
        // bgcolor
        snackBarView.setBackgroundColor(ContextCompat.getColor(activity, R.color.medix_gray));
        // move to top
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) snackBarView.getLayoutParams();
        params.gravity = Gravity.TOP;
        snackBarView.setLayoutParams(params);

        snackbar.show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(UploadService.PARAM_OUT_MSG);
        snackbar.setText(message);
        if(!snackbar.isShown()) snackbar.show();
    }
}
