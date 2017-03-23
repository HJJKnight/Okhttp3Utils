package com.example.knight.libraryutils.helper;

import com.example.knight.libraryutils.HttpInfo;
import com.example.knight.libraryutils.annotation.RequestMethod;
import com.example.knight.libraryutils.bean.DownloadFileInfo;
import com.example.knight.libraryutils.bean.UploadFileInfo;
import com.example.knight.libraryutils.callback.CallbackOk;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * OkHttpUtil业务类：负责处理所有业务
 * Created by knight on 2017/3/23.
 */
public class OkHttpHelper {

    //请求的相关参数
    private HttpInfo httpInfo;

    private HttpHelper httpHelper;
    private DownUploadHelper downUploadHelper;
    private DownloadFileInfo downloadFileInfo;
    private UploadFileInfo uploadFileInfo;
    private OkHttpClient.Builder clientBuilder;
    private @RequestMethod int requsetMethod;
    private CallbackOk callback;
    private Request request;
    private OkHttpClient httpClient;

    private OkHttpHelper(Builder builder) {
        httpInfo = builder.httpInfo;

    }

    private static final class Builder {

        private HttpInfo httpInfo;
        private HelperInfo helperInfo;
        private DownloadFileInfo downloadFileInfo;
        private UploadFileInfo uploadFileInfo;
        private OkHttpClient.Builder clientBuilder;
        private @RequestMethod int rquestMethos;
        private CallbackOk callback;

        public Builder(){}

        public OkHttpHelper build(){
            return new OkHttpHelper(this);
        }

        public Builder httpInfo(HttpInfo httpInfo){
            this.httpInfo = httpInfo;
            return this;
        }

        public Builder helperInfo(HelperInfo helperInfo){
            this.helperInfo = helperInfo;
            return this;
        }

        public Builder downloadFileInfo(DownloadFileInfo downloadFileInfo){
            this.downloadFileInfo = downloadFileInfo;
            return this;
        }

        public Builder uploadFileInfo(UploadFileInfo uploadFileInfo){
            this.uploadFileInfo = uploadFileInfo;
            return this;
        }

        public Builder clientBuilder(OkHttpClient.Builder clientBuilder){
            this.clientBuilder = clientBuilder;
            return this;
        }

        public Builder requestMethod(@RequestMethod int requestMethod){
            this.rquestMethos = requestMethod;
            return this;
        }

        public Builder callBack(CallbackOk callback){
            this.callback = callback;
            return this;
        }
    }

    public HttpInfo getHttpInfo() {
        return httpInfo;
    }

    public HttpHelper getHttpHelper() {
        return httpHelper;
    }

    public DownUploadHelper getDownUploadHelper() {
        return downUploadHelper;
    }

    public DownloadFileInfo getDownloadFileInfo() {
        return downloadFileInfo;
    }

    public UploadFileInfo getUploadFileInfo() {
        return uploadFileInfo;
    }

    public OkHttpClient.Builder getClientBuilder() {
        return clientBuilder;
    }

    public int getRequsetMethod() {
        return requsetMethod;
    }

    public CallbackOk getCallback() {
        return callback;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequset(Request requset){
        this.request = request;
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(OkHttpClient httpClient){
        this.httpClient = httpClient;
    }
}
