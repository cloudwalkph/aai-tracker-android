package com.cloudwalkph.aaitrackerandroid.eventSelection;

import com.cloudwalkph.aaitrackerandroid.lib.model.events.Event;
import com.cloudwalkph.aaitrackerandroid.lib.model.events.Location;

import io.realm.RealmList;

/**
 * Created by trick.sunga on 06/12/2016.
 */

public interface EventSelectionView {

    String TAG = EventSelectionView.class.getSimpleName();

    void setProgressDialogVisible(boolean visible);

    void setEvents(RealmList<Event> eventRealmList);

    void setLocations(RealmList<Location> locationRealmList);

    void setErrors(String message);
}
