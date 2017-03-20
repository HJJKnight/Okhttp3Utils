package com.example.knight.libraryutils.helper;

import com.example.knight.libraryutils.HttpInfo;

import okhttp3.OkHttpClient;

/**
 * Created by knight on 2017/3/20.
 */

public class BaseHelper {

    OkHttpClient httpClient;
    protected String tag;
    protected String timeStamp;
    protected boolean showHttpLog;
    protected String requestTag;

    BaseHelper() {

    }

    BaseHelper(HelperInfo info) {
        tag = info.getLogTAG();
        timeStamp = info.getTimeStamp();
        showHttpLog = info.isShowHttpLog();
        requestTag = info.getRequestTag();


    }
}
