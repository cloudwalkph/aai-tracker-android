package com.cloudwalkph.aaitrackerandroid.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

import com.cloudwalkph.aaitrackerandroid.lib.model.LocalAnswer;
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
                RealmResults<LocalAnswer> pollAnswerBodyRealmResults = realm
                        .where(LocalAnswer.class)
                        .equalTo("isPosted", false)
                        .findAll();

                String message = "No connection.";
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction(UploadServiceReceiver.ACTION_RESP);
                broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);

                if(isInternetAvailable()) {
                    if(pollAnswerBodyRealmResults.size() == 0) {
                        message = "All answers has been uploaded.";
                    } else {
                        int unPosted = pollAnswerBodyRealmResults.size();
                        for (LocalAnswer localAnswer : pollAnswerBodyRealmResults) {
                            message = "Uploading: "+unPosted+" answers.";
                            broadcastIntent.putExtra(PARAM_OUT_MSG, message);
                            sendBroadcast(broadcastIntent);
                            SystemClock.sleep(3000);

                            String uploadedFilename = uploadWorker.postImage(localAnswer.origImage);

                            realm.beginTransaction();
                            localAnswer.image = uploadedFilename;
                            PollAnswerResponse pollAnswerResponse = uploadWorker.postAnswer(localAnswer);
                            localAnswer.isPosted = true;

                            realm.copyToRealmOrUpdate(localAnswer);
                            realm.copyToRealmOrUpdate(pollAnswerResponse.getData());
                            realm.commitTransaction();
                            unPosted--;
                        }
                    }
                } else {
                    if(pollAnswerBodyRealmResults.size() > 0) {
                        message = "No connection. Pending: "+pollAnswerBodyRealmResults.size()+" answers.";
                    }
                }
                broadcastIntent.putExtra(PARAM_OUT_MSG, message);
                sendBroadcast(broadcastIntent);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(realm != null) {
                    realm.close();
                }
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
