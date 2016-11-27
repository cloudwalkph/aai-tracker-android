package com.cloudwalkph.aaitrackerandroid.lib.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

import com.cloudwalkph.aaitrackerandroid.lib.model.LocalAnswer;
import com.cloudwalkph.aaitrackerandroid.lib.service.api.PollAnswerResponse;

import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by trick.sunga on 24/11/2016.
 */

public class UploadService extends IntentService {

    public UploadService() {
        super("UploadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final UploadWorker uploadWorker = new UploadWorkerImpl();

        while(true) {
            SystemClock.sleep(10000);
            Realm realm = null;
            try {
                realm = Realm.getDefaultInstance();
                RealmResults<LocalAnswer> pollAnswerBodyRealmResults = realm
                        .where(LocalAnswer.class)
                        .equalTo("isPosted", false)
                        .findAll();
                for (LocalAnswer localAnswer : pollAnswerBodyRealmResults) {
                    SystemClock.sleep(3000);

                    String uploadedFilename = uploadWorker.postImage(localAnswer.origImage);

                    realm.beginTransaction();
                    localAnswer.image = uploadedFilename;
                    PollAnswerResponse pollAnswerResponse = uploadWorker.postAnswer(localAnswer);
                    localAnswer.isPosted = true;

                    realm.copyToRealmOrUpdate(localAnswer);
                    realm.copyToRealmOrUpdate(pollAnswerResponse.getData());
                    realm.commitTransaction();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(realm != null) {
                    realm.close();
                }
            }
        }
    }
}
