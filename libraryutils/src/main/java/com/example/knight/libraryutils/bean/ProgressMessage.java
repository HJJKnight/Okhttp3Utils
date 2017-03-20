package com.example.knight.libraryutils.bean;

import com.example.knight.libraryutils.callback.ProgressCallback;

/**
 * 进度回调信息
 * Created by knight on 2017/3/20.
 */

public class ProgressMessage extends OkMessage {

    public int percent;
    public long byteWritten;
    public long contentLength;
    public boolean done;
    public ProgressCallback progressCallback;

    public ProgressMessage(int what, int percent, long bytesWritten, long contentLength, boolean done, ProgressCallback progressCallback) {
        this.what = what;
        this.percent = percent;
        this.byteWritten = bytesWritten;
        this.contentLength = contentLength;
        this.done = done;
        this.progressCallback = progressCallback;
    }
}
