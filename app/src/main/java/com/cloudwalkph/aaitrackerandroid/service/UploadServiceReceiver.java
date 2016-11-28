package com.cloudwalkph.aaitrackerandroid.service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;

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
        snackbar.show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(UploadService.PARAM_OUT_MSG);
        snackbar.setText(message);
    }
}
