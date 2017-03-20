package com.example.knight.libraryutils;

import android.text.TextUtils;

import com.example.knight.libraryutils.bean.DownloadFileInfo;
import com.example.knight.libraryutils.bean.UploadFileInfo;
import com.example.knight.libraryutils.callback.ProgressCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Http 请求实体类
 * Created by knight on 2017/3/20.
 */

public class HttpInfo {

    //请求地址
    private String url;
    //请求参数
    private Map<String, String> params;
    //请求头
    private Map<String, String> heads;
    //上传文件
    private List<UploadFileInfo> uploadFiles;
    //下载文件
    private List<DownloadFileInfo> downloadFiles;

    //返回状态码
    private int resultCode;
    //返回结果
    private String retDetail;
    //网络返回码
    private int netCode;

    public HttpInfo(Builder builder) {
        this.url = builder.url;
        this.params = builder.params;
        this.uploadFiles = builder.uploadFiles;
        this.downloadFiles = builder.downloadFiles;
        this.heads = builder.heads;
    }

    public static Builder Builder() {
        return new Builder();
    }

    public static final class Builder {
        private String url;
        private Map<String, String> params;
        private Map<String, String> heads;
        private List<UploadFileInfo> uploadFiles;
        private List<DownloadFileInfo> downloadFiles;

        public Builder() {

        }

        public HttpInfo build() {
            return new HttpInfo(this);
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        /**
         * 添加请求接口参数
         *
         * @param params
         * @return
         */
        public Builder addParams(Map<String, String> params) {
            if (params == null)
                return this;
            if (this.params == null) {
                this.params = params;
            } else {
                this.params.putAll(params);
            }
            return this;
        }

        /**
         * 添加单个的请求参数
         *
         * @param key
         * @param value
         * @return
         */
        public Builder addParam(String key, String value) {
            if (this.params == null) {
                params = new HashMap<>();
            }
            if (!TextUtils.isEmpty(key)) {
                value = value == null ? "" : value;
                this.params.put(key, value);
            }
            return this;
        }

        /**
         * 添加请求协议头参数
         *
         * @param heads
         * @return
         */
        public Builder addHeaders(Map<String, String> heads) {
            if (null == heads) {
                return this;
            }
            if (this.heads == null) {
                this.heads = heads;
            } else {
                this.heads.putAll(heads);
            }
            return this;
        }

        /**
         * 添加单个的请求头协议参数
         *
         * @param key
         * @param value
         * @return
         */
        public Builder addHead(String key, String value) {
            if (this.heads == null) {
                this.heads = new HashMap<>();
            }
            if (!TextUtils.isEmpty(key)) {
                value = value == null ? "" : value;
                this.heads.put(key, value);
            }
            return this;
        }

        /**
         * 添加上传文件
         *
         * @param interfaceParamName 上传接口参数名称
         * @param filePathWithName   上传文件的路径（包含文件名）
         * @return
         */
        public Builder addUploadFile(String interfaceParamName, String filePathWithName) {
            addUploadFile(interfaceParamName, filePathWithName, null);
            return this;
        }

        /**
         * 添加上传文件
         *
         * @param interfaceParamName 接口参数名称
         * @param filePathWithName   上传的文件路径：包含文件名
         * @param progressCallback   上传进度回调接口
         * @return
         */
        public Builder addUploadFile(String interfaceParamName, String filePathWithName, ProgressCallback progressCallback) {
            if (this.uploadFiles == null) {
                this.uploadFiles = new ArrayList<>();
            }
            if (!TextUtils.isEmpty(filePathWithName)) {
                this.uploadFiles.add(new UploadFileInfo(filePathWithName, interfaceParamName, progressCallback));
            }
            return this;
        }

        /**
         * 添加上传文件
         *
         * @param url                上传文件接口地址
         * @param interfaceParamName 接口参数名称
         * @param filePathWithName   上传的文件路径：包含文件名
         * @param progressCallback   上传进度回调接口
         */
        public Builder addUploadFile(String url, String interfaceParamName, String filePathWithName, ProgressCallback progressCallback) {
            if (this.uploadFiles == null) {
                this.uploadFiles = new ArrayList<>();
            }
            if (!TextUtils.isEmpty(filePathWithName)) {
                this.uploadFiles.add(new UploadFileInfo(url, filePathWithName, interfaceParamName, progressCallback));
            }
            return this;
        }

        /**
         * 添加一个上传集合
         *
         * @param uploadFiles
         * @return
         */
        public Builder addUploadFiles(List<UploadFileInfo> uploadFiles) {
            if (uploadFiles == null) {
                return this;
            }
            if (this.uploadFiles == null) {
                this.uploadFiles = uploadFiles;
            } else {
                this.uploadFiles.addAll(uploadFiles);
            }
            return this;
        }

        /**
         * 添加下载文件
         *
         * @param url
         * @param saveFileName
         * @return
         */
        public Builder addDownloadFile(String url, String saveFileName) {
            addDownloadFile(url, null, saveFileName, null);
            return this;
        }

        /**
         * 添加下载文件
         *
         * @param url              下载文件的网络地址
         * @param saveFileName     文件保存名称：不包括扩展名
         * @param progressCallback 下载进度回调接口
         */
        public Builder addDownloadFile(String url, String saveFileName, ProgressCallback progressCallback) {
            addDownloadFile(url, null, saveFileName, progressCallback);
            return this;
        }

        public Builder addDownloadFile(String url, String saveFileDir, String saveFileName, ProgressCallback progressCallback) {
            if (this.downloadFiles == null) {
                this.downloadFiles = new ArrayList<>();
            }
            if (!TextUtils.isEmpty(url)) {
                this.downloadFiles.add(new DownloadFileInfo(url, saveFileDir, saveFileName, progressCallback));
            }
            return this;
        }

        public Builder addDownloadFile(DownloadFileInfo downloadFileInfo) {
            if (downloadFileInfo == null) {
                return this;
            }
            if (this.downloadFiles == null) {
                this.downloadFiles = new ArrayList<>();
            }
            this.downloadFiles.add(downloadFileInfo);
            return this;
        }

        public Builder addDownloadFiles(List<DownloadFileInfo> downloadFiles) {
            if (downloadFiles == null) {
                return this;
            }
            if (this.downloadFiles == null) {
                this.downloadFiles = downloadFiles;
            } else {
                this.downloadFiles.addAll(downloadFiles);
            }
            return this;
        }
    }

    //**请求返回常量定义**/
    public final static int SUCCESS = 1;
    public final static int NonNetwork = 2;
    public final static int ProtocolException = 3;
    public final static int NoResult = 4;
    public final static int CheckURL = 5;
    public final static int CheckNet = 6;
    public final static int ConnectionTimeOut = 7;
    public final static int WriteAndReadTimeOut = 8;
    public final static int ConnectionInterruption = 9;
    public final static int NetworkOnMainThreadException = 10;
    public final static int Message = 11;

    public HttpInfo packInfo(int netCode, int retCode, String retDetail) {
        this.netCode = netCode;
        this.resultCode = retCode;
        switch (retCode) {
            case NonNetwork:
                this.retDetail = "网络中断";
                break;
            case SUCCESS:
                this.retDetail = "发送请求成功";
                break;
            case ProtocolException:
                this.retDetail = "请检查协议类型是否正确";
                break;
            case NoResult:
                this.retDetail = "无法获取返回信息(服务器内部错误)";
                break;
            case CheckURL:
                this.retDetail = "请检查请求地址是否正确";
                break;
            case CheckNet:
                this.retDetail = "请检查网络连接是否正常";
                break;
            case ConnectionTimeOut:
                this.retDetail = "连接超时";
                break;
            case WriteAndReadTimeOut:
                this.retDetail = "读写超时";
                break;
            case ConnectionInterruption:
                this.retDetail = "连接中断";
                break;
            case NetworkOnMainThreadException:
                this.retDetail = "不允许在UI线程中进行网络操作";
                break;
            case Message:
                this.retDetail = "";
                break;
        }
        if (!TextUtils.isEmpty(retDetail)) {
            this.retDetail = retDetail;
        }
        return this;
    }

    public int getRetCode() {
        return resultCode;
    }

    public boolean isSuccessful() {
        return this.resultCode == SUCCESS;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRetDetail() {
        return retDetail;
    }

    public void setRetDetail(String retDetail) {
        this.retDetail = retDetail;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public List<UploadFileInfo> getUploadFiles() {
        return uploadFiles;
    }

    public List<DownloadFileInfo> getDownloadFiles() {
        return downloadFiles;
    }

    public Map<String, String> getHeads() {
        return heads;
    }

    public int getNetCode() {
        return netCode;
    }

}
