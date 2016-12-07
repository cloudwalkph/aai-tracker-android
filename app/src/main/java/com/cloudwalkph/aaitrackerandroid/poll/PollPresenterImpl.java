package com.cloudwalkph.aaitrackerandroid.poll;

import com.cloudwalkph.aaitrackerandroid.lib.model.CurrentEventLocation;
import com.cloudwalkph.aaitrackerandroid.lib.model.localEventAnswers.LocalEventAnswer;
import com.cloudwalkph.aaitrackerandroid.lib.model.localEventAnswers.LocalPollAnswer;
import com.cloudwalkph.aaitrackerandroid.lib.model.TokenOwner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by trick.sunga on 22/11/2016.
 */

public class PollPresenterImpl implements PollPresenter {
    private PollView view;

    public PollPresenterImpl(PollView view) {
        this.view = view;
    }

    @Override
    public int loadHitCount() {
        CurrentEventLocation currentEventLocation = CurrentEventLocation.getInstance();

        Realm realm = Realm.getDefaultInstance();
        RealmResults<LocalEventAnswer> localEventAnswerRealmResults = realm
                .where(LocalEventAnswer.class)
                .equalTo("eventId", currentEventLocation.getEventId())
                .equalTo("eventLocationId", currentEventLocation.getEventLocationId())
                .findAll();
        return localEventAnswerRealmResults.size();
    }

    @Override
    public void saveAnswer(String age, String gender, String image, String name, String contactNumber, String email) {
        view.setProgressDialogVisible(true);

        CurrentEventLocation currentEventLocation = CurrentEventLocation.getInstance();

        Integer eventId = currentEventLocation.getEventId();
        Integer eventLocationId = currentEventLocation.getEventLocationId();
        String uuid = UUID.randomUUID().toString();
        String userId = String.valueOf(TokenOwner.getInstance().getId());

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hitDate = df.format(c.getTime());

        LocalPollAnswer answer1 = new LocalPollAnswer();
        answer1.pollId = "1";
        answer1.value = age;

        LocalPollAnswer answer2 = new LocalPollAnswer();
        answer2.pollId = "2";
        answer2.value = gender;

        RealmList<LocalPollAnswer> localPollAnswers = new RealmList<>();
        localPollAnswers.add(answer1);
        localPollAnswers.add(answer2);

        LocalEventAnswer localEventAnswer = new LocalEventAnswer();
        localEventAnswer.eventId = eventId;
        localEventAnswer.eventLocationId = eventLocationId;
        localEventAnswer.uuid = uuid;
        localEventAnswer.userId = userId;
        localEventAnswer.origImage = image;
        localEventAnswer.hitDate = hitDate;
        localEventAnswer.name = name;
        localEventAnswer.contactNumber = contactNumber;
        localEventAnswer.email = email;
        localEventAnswer.localPollAnswers = localPollAnswers;
        localEventAnswer.isPosted = false;

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(localEventAnswer);
        realm.commitTransaction();
        realm.close();

        view.setProgressDialogVisible(false);
        view.showSuccessDialog();
        view.resetForm();
    }
}
