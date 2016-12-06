package com.cloudwalkph.aaitrackerandroid.eventSelection;

import com.cloudwalkph.aaitrackerandroid.lib.model.events.Event;
import com.cloudwalkph.aaitrackerandroid.lib.model.events.Location;

import io.realm.RealmList;

/**
 * Created by trick.sunga on 06/12/2016.
 */

public interface OnEventSelectionWorkerListener {
    void onLoadEventsSuccess(RealmList<Event> eventRealmList);
    void onLoadEventsFail(String message);

    void onLoadLocationsSuccess(RealmList<Location> locationRealmList);
    void onLoadLocationsFail(String message);
}
