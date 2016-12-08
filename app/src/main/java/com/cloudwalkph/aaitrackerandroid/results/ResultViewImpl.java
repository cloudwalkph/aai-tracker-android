package com.cloudwalkph.aaitrackerandroid.results;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cloudwalkph.aaitrackerandroid.R;
import com.cloudwalkph.aaitrackerandroid.lib.model.localEventAnswers.LocalEventAnswer;
import com.cloudwalkph.aaitrackerandroid.lib.ui.BaseFragment;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenController;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenControllerImpl;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenControllerProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.RealmResults;

/**
 * Created by trick.sunga on 08/12/2016.
 */

public class ResultViewImpl extends BaseFragment implements ResultView, ScreenControllerProvider {
    public static final String TAG = ResultViewImpl.class.getSimpleName();
    private ResultPresenter presenter;

    View rootView;
    ProgressDialog progressDialog;

    @BindView(R.id.recyclerView)
    RealmRecyclerView recyclerView;

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
        rootView = inflater.inflate(R.layout.fragment_result, container, false);
        initializePresenter();
        initializeScreenController();
        initializeView(rootView);

        initializeData();

        setHasOptionsMenu(true);
        return rootView;
    }

    private void initializePresenter(){
        presenter = new ResultPresenterImpl(this);
    }

    private void initializeScreenController() {
        screenController = new ScreenControllerImpl(((AppCompatActivity) getActivity()).getSupportActionBar(), getActivity().getSupportFragmentManager(), R.id.container);
    }

    private void initializeView(View rootView){
        ButterKnife.bind(this, rootView);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
    }

    private void initializeData() {
        presenter.loadAnswers();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.hitCount);

        item.setTitle("");
    }

    @Override
    public void setProgressDialogVisible(boolean visible) {
        Log.d(TAG,"setProgressBarVisible "+visible );
        if(visible) {
            progressDialog.setTitle("Please wait...");
            progressDialog.setMessage("Loading data, please wait...");
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void loadResults(RealmResults<LocalEventAnswer> localEventAnswerRealmResults) {
        LocalEventAnswerAdapter localEventAnswerAdapter = new LocalEventAnswerAdapter(getContext(), localEventAnswerRealmResults, true, true);
        recyclerView.setAdapter(localEventAnswerAdapter);
    }
}
