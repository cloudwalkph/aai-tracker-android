package com.cloudwalkph.aaitrackerandroid.lib.ui;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by patsoo on 14/01/2016.
 */
public class UiUtils {

    public static void hideKeyboard(Context context, View field) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(field.getWindowToken(), 0);
    }
}
