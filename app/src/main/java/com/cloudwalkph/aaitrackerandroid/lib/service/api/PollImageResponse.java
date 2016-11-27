package com.cloudwalkph.aaitrackerandroid.lib.service.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by trick.sunga on 23/11/2016.
 */
public class PollImageResponse {
    private int status;
    private Data data;

    public class Data {

        @SerializedName("file_name")
        private String fileName;

        /**
         *
         * @return
         * The fileName
         */
        public String getFileName() {
            return fileName;
        }

        /**
         *
         * @param fileName
         * The file_name
         */
        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

    }

    /**
     *
     * @return
     * The status
     */
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The data
     */
    public Data getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(Data data) {
        this.data = data;
    }
}
