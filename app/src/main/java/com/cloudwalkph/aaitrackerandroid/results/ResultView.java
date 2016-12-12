package com.cloudwalkph.aaitrackerandroid.results;

import com.cloudwalkph.aaitrackerandroid.lib.model.localEventAnswers.LocalEventAnswer;

import io.realm.RealmResults;

/**
 * Created by trick.sunga on 08/12/2016.
 */

public interface ResultView {
    String TAG = ResultView.class.getSimpleName();

    void setProgressDialogVisible(boolean visible);

    void loadResults(RealmResults<LocalEventAnswer> localEventAnswerRealmResults);
}
