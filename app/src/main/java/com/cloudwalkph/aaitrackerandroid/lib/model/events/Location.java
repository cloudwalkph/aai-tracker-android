package com.cloudwalkph.aaitrackerandroid.lib.model.events;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by trick.sunga on 06/12/2016.
 */

public class Location extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

}
