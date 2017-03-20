package com.example.knight.libraryutils.callback;

import com.example.knight.libraryutils.HttpInfo;

import java.io.IOException;

/**
 * Created by knight on 2017/3/20.
 */

public interface CallbackOk {

    /**
     * 该方法回调已经切换回UI线程
     * @param info
     * @throws IOException
     */
    void onResponse(HttpInfo info) throws IOException;
}
