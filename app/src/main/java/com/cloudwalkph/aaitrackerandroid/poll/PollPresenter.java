package com.cloudwalkph.aaitrackerandroid.poll;

import okhttp3.MultipartBody;

/**
 * Created by trick.sunga on 22/11/2016.
 */

public interface PollPresenter {

    int loadHitCount();

    void saveAnswer(String age, String gender, String image, String name, String contactNumber, String email);
}
