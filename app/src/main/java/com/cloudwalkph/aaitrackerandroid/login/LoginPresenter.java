package com.cloudwalkph.aaitrackerandroid.login;

import android.view.View;

import com.cloudwalkph.aaitrackerandroid.lib.model.AccessToken;

/**
 * Created by patsoo on 09/01/16.
 */
public interface LoginPresenter {

    void login();
    void fetchTokenOwner(AccessToken token);
}
