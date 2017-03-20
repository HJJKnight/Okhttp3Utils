package com.example.knight.libraryutils.bean;

import com.example.knight.libraryutils.HttpInfo;
import com.example.knight.libraryutils.callback.ProgressCallback;

/**
 * 下载响应回调
 * Created by knight on 2017/3/20.
 */

public class DownloadMessage extends OkMessage {

    public String filePath;
    public HttpInfo info;
    public ProgressCallback progressCallback;


    public DownloadMessage(int what, String filePath, HttpInfo info, ProgressCallback progressCallback) {
        this.what = what;
        this.filePath = filePath;
        this.info = info;
        this.progressCallback = progressCallback;
    }
}
