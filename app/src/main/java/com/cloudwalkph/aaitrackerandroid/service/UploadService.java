package com.cloudwalkph.aaitrackerandroid.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

import com.cloudwalkph.aaitrackerandroid.lib.model.localEventAnswers.LocalEventAnswer;
import com.cloudwalkph.aaitrackerandroid.service.api.PollAnswerResponse;

import java.io.IOException;
import java.net.InetAddress;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by trick.sunga on 24/11/2016.
 */

public class UploadService extends IntentService {
    public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";

    public UploadService() {
        super("UploadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final UploadWorker uploadWorker = new UploadWorkerImpl();

        while(true) {
            Realm realm = null;
            try {
                realm = Realm.getDefaultInstance();
                RealmResults<LocalEventAnswer> localEventAnswerRealmResults = realm
                        .where(LocalEventAnswer.class)
                        .equalTo("isPosted", false)
                        .findAll();

                String message = "No connection.";
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction(UploadServiceReceiver.ACTION_RESP);
                broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);

                if(isInternetAvailable()) {
                    if(localEventAnswerRealmResults.size() == 0) {
                        message = "All answers has been uploaded.";
                    } else {
                        int unPosted = localEventAnswerRealmResults.size();
                        for (LocalEventAnswer localEventAnswer : localEventAnswerRealmResults) {
                            message = "Uploading: "+unPosted+" answers.";
                            broadcastIntent.putExtra(PARAM_OUT_MSG, message);
                            sendBroadcast(broadcastIntent);
                            SystemClock.sleep(3000);

                            String uploadedFilename = uploadWorker.postImage(localEventAnswer.origImage);

                            realm.beginTransaction();
                            localEventAnswer.image = uploadedFilename;
                            PollAnswerResponse pollAnswerResponse = uploadWorker.postAnswer(localEventAnswer);
                            localEventAnswer.isPosted = true;

                            realm.copyToRealmOrUpdate(localEventAnswer);
                            realm.copyToRealmOrUpdate(pollAnswerResponse.getData());
                            realm.commitTransaction();
                            unPosted--;
                        }
                    }
                } else {
                    if(localEventAnswerRealmResults.size() > 0) {
                        message = "No connection. Pending: "+localEventAnswerRealmResults.size()+" answers.";
                    }
                }
                broadcastIntent.putExtra(PARAM_OUT_MSG, message);
                sendBroadcast(broadcastIntent);
                realm.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SystemClock.sleep(5000);
        }
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("aai-tracker.medix.ph"); //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
}
