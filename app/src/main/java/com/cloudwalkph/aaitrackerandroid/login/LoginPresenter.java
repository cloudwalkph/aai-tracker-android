package com.cloudwalkph.aaitrackerandroid.login;

import com.cloudwalkph.aaitrackerandroid.lib.model.users.AccessToken;

/**
 * Created by patsoo on 09/01/16.
 */
public interface LoginPresenter {

    void login();
    void fetchTokenOwner(AccessToken token);
}
