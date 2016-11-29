package com.cloudwalkph.aaitrackerandroid.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cloudwalkph.aaitrackerandroid.R;
import com.cloudwalkph.aaitrackerandroid.lib.ui.BaseFragment;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenController;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenControllerImpl;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenControllerProvider;
import com.cloudwalkph.aaitrackerandroid.lib.ui.UiUtils;
import com.cloudwalkph.aaitrackerandroid.poll.PollView;
import com.cloudwalkph.aaitrackerandroid.poll.PollViewImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by patsoo on 09/01/16.
 */
public class LoginViewImpl extends BaseFragment implements LoginView, ScreenControllerProvider {
    public static final String TAG = LoginViewImpl.class.getSimpleName();
    private LoginPresenter presenter;

    View rootView;
    ProgressDialog progressDialog;

    @Bind(R.id.login_username)
    EditText username;
    @Bind(R.id.login_password)
    EditText password;
    @Bind(R.id.login_submit_button)
    Button submitButton;

    private ScreenController screenController;

    @Override
    public String getScreenTitle() {
        return "";
    }

    @Override
    public ScreenController getScreenController() {
        return screenController;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        initializePresenter();
        initializeScreenController();
        initializeView(rootView);
        return rootView;
    }

    private void initializePresenter(){
        presenter = new LoginPresenterImpl(this);
    }

    private void initializeScreenController() {
        screenController = new ScreenControllerImpl(((AppCompatActivity) getActivity()).getSupportActionBar(), getActivity().getSupportFragmentManager(), R.id.container);
    }

    private void initializeView(View rootView){
        ButterKnife.bind(this, rootView);

        username.setText("insite@test.com");
        password.setText("password");
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
    }

    @OnClick(R.id.login_submit_button)
    public void login(View view) {
        presenter.login();
        hideKeyboard();
    }

    @Override
    public void setProgressDialogVisible(boolean visible) {
        Log.d(TAG,"setProgressBarVisible "+visible );
        if(visible) {
            progressDialog.setTitle("Please wait...");
            progressDialog.setMessage("Logging in, please wait...");
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void setContainerVisible(boolean visible) {
        Log.d(TAG, "setContainerVisible " + visible);
    }

    @Override
    public String getLoginNameValue() {
        return username.getText().toString();
    }

    @Override
    public String getPasswordValue() {
        return password.getText().toString();
    }

    @Override
    public void setLoginSuccessful() {
        Log.d(TAG, "setLoginSuccessful ");
        screenController.navigateToScreen(new PollViewImpl(), PollView.TAG);
    }

    @Override
    public void setLoginFail(String message) {
        Log.d(TAG, "setLoginFail ");
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setLoginNameError() {
        Log.d(TAG,"setLoginNameError ");
        username.setError("This field can not be blank");
    }

    @Override
    public void setPasswordError() {
        Log.d(TAG,"setPasswordError ");
        password.setError("This field can not be blank");
    }

    @Override
    public void hideKeyboard() {
        UiUtils.hideKeyboard(getActivity(), password);
    }

}
