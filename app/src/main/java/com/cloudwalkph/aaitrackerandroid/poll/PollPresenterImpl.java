package com.cloudwalkph.aaitrackerandroid.poll;

import com.cloudwalkph.aaitrackerandroid.lib.model.EventAnswer;
import com.cloudwalkph.aaitrackerandroid.poll.api.AnswerBody;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;
import okhttp3.MultipartBody;

/**
 * Created by trick.sunga on 22/11/2016.
 */

public class PollPresenterImpl implements PollPresenter, OnPollAnswerWorkerListener {
    private PollView view;

    public PollPresenterImpl(PollView view) {
        this.view = view;
    }

    @Override
    public void postAnswer(MultipartBody.Part image) {
        view.setContainerVisible(false);
        view.setProgressDialogVisible(true);

        PollWorker pollWorker = new PollWorkerImpl(this);
        pollWorker.postImage(image);
    }

    @Override
    public void onPollImageUploadSuccess(String filename) {
        // get inputs and image
        String eventId = "1";
        String eventLocationId = "3";
        String uuid = UUID.randomUUID().toString();
        String userId = "1";

        AnswerBody answer1 = new AnswerBody();
        answer1.pollId = "1";
        answer1.value = view.getAge();

        AnswerBody answer2 = new AnswerBody();
        answer2.pollId = "2";
        answer2.value = view.getGender();

        RealmList<AnswerBody> answers = new RealmList<>();
        answers.add(answer1);
        answers.add(answer2);

        PollWorker pollWorker = new PollWorkerImpl(this);
        pollWorker.postAnswer(eventId, eventLocationId, uuid, userId, filename, answers);
    }

    @Override
    public void onPollImageUploadFail(String message) {

    }

    @Override
    public void onPollAnswerSuccess(EventAnswer eventAnswer) {
        view.setContainerVisible(true);
        view.setProgressDialogVisible(false);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(eventAnswer);
        realm.commitTransaction();
    }

    @Override
    public void onPollAnswerFail(String message) {

    }
}
