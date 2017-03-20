package com.example.knight.libraryutils.callback;

import com.example.knight.libraryutils.HttpInfo;

/**
 * 进度回调抽象类
 * Created by knight on 2017/3/20.
 */

public abstract class ProgressCallbackAbs {

    public abstract void onResponseMain(String filePath, HttpInfo info);

    public abstract void onResponseSync(String filePath, HttpInfo info);

    public abstract void onProgressAsync(int percent, long bytesWritten, long contentLength, boolean done);

    public abstract void onProgressMain(int percent, long bytesWritten, long contentLength, boolean done);
}
