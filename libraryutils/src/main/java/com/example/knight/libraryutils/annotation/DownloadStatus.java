package com.example.knight.libraryutils.annotation;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by knight on 2017/3/20.
 */
@StringDef({DownloadStatus.INIT, DownloadStatus.DOWNLOADING, DownloadStatus.PAUSE, DownloadStatus.COMPLETED})
@Retention(RetentionPolicy.SOURCE)
public @interface DownloadStatus {
    //初始化状态
    String INIT = "INIT";
    //下载状态
    String DOWNLOADING = "DOWNLOADING";
    //暂停状态
    String PAUSE = "PAUSE";
    //完成状态
    String COMPLETED = "COMPLETED";
}
