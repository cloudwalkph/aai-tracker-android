package com.cloudwalkph.aaitrackerandroid.poll;

import com.cloudwalkph.aaitrackerandroid.lib.model.LocalAnswer;
import com.cloudwalkph.aaitrackerandroid.lib.model.LocalPollAnswer;
import com.cloudwalkph.aaitrackerandroid.lib.model.TokenOwner;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by trick.sunga on 22/11/2016.
 */

public class PollPresenterImpl implements PollPresenter {
    private PollView view;

    public PollPresenterImpl(PollView view) {
        this.view = view;
    }

    @Override
    public void saveAnswer(String age, String gender, String image) {
        view.setContainerVisible(false);
        view.setProgressDialogVisible(true);

        String eventId = "1";
        String eventLocationId = "3";
        String uuid = UUID.randomUUID().toString();
        String userId = String.valueOf(TokenOwner.getInstance().getId());

        LocalPollAnswer answer1 = new LocalPollAnswer();
        answer1.pollId = "1";
        answer1.value = age;

        LocalPollAnswer answer2 = new LocalPollAnswer();
        answer2.pollId = "2";
        answer2.value = gender;

        RealmList<LocalPollAnswer> localPollAnswers = new RealmList<>();
        localPollAnswers.add(answer1);
        localPollAnswers.add(answer2);

        LocalAnswer localAnswer = new LocalAnswer();
        localAnswer.eventId = eventId;
        localAnswer.eventLocationId = eventLocationId;
        localAnswer.uuid = uuid;
        localAnswer.userId = userId;
        localAnswer.origImage = image;
        localAnswer.localPollAnswers = localPollAnswers;
        localAnswer.isPosted = false;

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(localAnswer);
        realm.commitTransaction();
        realm.close();

        view.setContainerVisible(true);
        view.setProgressDialogVisible(false);
    }
}
