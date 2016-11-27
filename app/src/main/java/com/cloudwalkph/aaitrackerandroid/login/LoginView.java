package com.cloudwalkph.aaitrackerandroid.login;

/**
 * Created by patsoo on 09/01/16.
 */
public interface LoginView {

    String TAG = LoginView.class.getSimpleName();

    void setProgressDialogVisible(boolean visible);

    void setContainerVisible(boolean visible);

    String getLoginNameValue();

    String getPasswordValue();

    void setLoginSuccessful();

    void setLoginFail(String message);

    void setLoginNameError();

    void setPasswordError();

    void hideKeyboard();
}
