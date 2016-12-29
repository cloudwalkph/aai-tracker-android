package com.cloudwalkph.aaitrackerandroid.results;

import com.cloudwalkph.aaitrackerandroid.lib.model.localEventAnswers.LocalEventAnswer;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by trick.sunga on 08/12/2016.
 */

public class ResultPresenterImpl implements ResultPresenter {
    private ResultView view;

    public ResultPresenterImpl(ResultView view) {
        this.view = view;
    }


    @Override
    public void loadAnswers() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<LocalEventAnswer> localEventAnswers = realm
                .where(LocalEventAnswer.class)
                .findAll();
        view.loadResults(localEventAnswers);
    }
}
