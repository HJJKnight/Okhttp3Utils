package com.example.knight.libraryutils.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.knight.libraryutils.bean.CallbackMessage;
import com.example.knight.libraryutils.bean.DownloadMessage;
import com.example.knight.libraryutils.bean.ProgressMessage;
import com.example.knight.libraryutils.bean.UploadMessage;

/**
 * Created by knight on 2017/3/20.
 */

public class OkMainHandler extends Handler {

    /**
     * 网络请求回调标识
     */
    public static final int RESPONSE_CALLBACK = 0x01;

    /**
     * 进度回调标识
     */
    public static final int PROGRESS_CALLBACK = 0x02;

    /**
     * 上传结果回调标识
     */
    public static final int RESPONSE_UPLOAD_CALLBACK = 0x03;

    /**
     * 下载结果回调标识
     */
    public static final int RESPONSE_DOWNLOAD_CALLBACK = 0x04;

    private static OkMainHandler okMainHandler;

    public static OkMainHandler getInstance() {
        if (okMainHandler == null) {
            synchronized (OkMainHandler.class) {
                if (okMainHandler == null) {
                    okMainHandler = new OkMainHandler();
                }
            }
        }
        return okMainHandler;
    }

    public OkMainHandler() {
        super(Looper.getMainLooper());
    }


    @Override
    public void handleMessage(Message msg) {
        int what = msg.what;
        try {
            switch (what) {
                case RESPONSE_CALLBACK:
                    CallbackMessage callMsg = (CallbackMessage) msg.obj;
                    if (callMsg.callback != null) {
                        callMsg.callback.onResponse(callMsg.info);
                    }
                    break;
                case PROGRESS_CALLBACK:
                    ProgressMessage progressMsg = (ProgressMessage) msg.obj;
                    if (progressMsg != null) {
                        progressMsg.progressCallback.onProgressMain(progressMsg.percent, progressMsg.byteWritten, progressMsg.contentLength, progressMsg.done);
                    }
                    break;
                case RESPONSE_UPLOAD_CALLBACK:
                    UploadMessage uploadMsg = (UploadMessage) msg.obj;
                    if (uploadMsg != null) {
                        uploadMsg.progressCallback.onResponseMain(uploadMsg.filePath, uploadMsg.info);
                    }
                    break;
                case RESPONSE_DOWNLOAD_CALLBACK:
                    DownloadMessage downloadMsg = (DownloadMessage) msg.obj;
                    if (downloadMsg != null) {
                        downloadMsg.progressCallback.onResponseMain(downloadMsg.filePath, downloadMsg.info);
                    }
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        } catch (Exception e) {

        }

    }
}
