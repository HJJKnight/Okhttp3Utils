package com.example.knight.libraryutils.bean;

import com.example.knight.libraryutils.HttpInfo;
import com.example.knight.libraryutils.callback.CallbackOk;

/**
 * 响应信息回调
 * Created by knight on 2017/3/20.
 */

public class CallbackMessage extends OkMessage {

    public CallbackOk callback;
    public HttpInfo info;

    public CallbackMessage(int what, CallbackOk callback, HttpInfo info) {
        this.what = what;
        this.callback = callback;
        this.info = info;
    }
}
