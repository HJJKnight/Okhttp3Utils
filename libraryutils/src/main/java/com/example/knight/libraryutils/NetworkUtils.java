package com.example.knight.libraryutils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络工具类
 * Created by knight on 2017/3/16.
 */

public class NetworkUtils {

    private static NetworkUtils networkUtils;
    private final OkHttpClient okHttpClient;
    private Context context;

    private NetworkUtils(Context context) {
        okHttpClient = new OkHttpClient.Builder().build();
        this.context = context;
    }

    public static NetworkUtils getInstance(Context context) {
        if (networkUtils == null) {
            networkUtils = new NetworkUtils(context);
        }
        return networkUtils;
    }

    public void DownloadFile(String url, final onDownloadListener listener) {

        final String fileName = url.substring(url.lastIndexOf("/") + 1);
        Request request = new Request.Builder()
                .url(url).build();
       /*
       //确定下载的范围,添加此头,则服务器就可以跳过已经下载好的部分
       .addHeader("RANGE", "bytes=" + downloadLength + "-" + contentLength)*/

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onFailed("下载失败！");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                InputStream inputStream;
                byte[] buffer = new byte[1024 * 4];
                int len = 0;
                FileOutputStream outputStream;
                //下载地址是公共目录的Download 下面的对应的包名
                String savaDir = isSaveDirExist();
                if (savaDir != null) {
                    File tempFile = new File(savaDir, fileName);
                    long netFileSize = response.body().contentLength();
                    long localFileSize = 0L;

                    if (netFileSize == tempFile.length()) {
                        listener.onSuccess("文件已经存在");
                    } else {
                        inputStream = response.body().byteStream();
                        outputStream = new FileOutputStream(tempFile);

                        while ((len = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, len);
                            localFileSize += len;
                            int progress = (int) (localFileSize * 1.0f / netFileSize * 100);

                            listener.onDownloading(progress);
                        }

                        outputStream.flush();
                        listener.onSuccess("文件下载成功");

                        inputStream.close();
                        outputStream.close();
                    }
                } else {
                    listener.onFailed("存储空间读取失败");
                }
            }
        });

    }

    /**
     * 检查保存地址是否存在
     *
     * @return
     */
    private String isSaveDirExist() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File downloadFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File downloadPath = new File(downloadFile, context.getPackageName());
            if (!downloadPath.exists()) {
                downloadPath.mkdirs();
            }
            return downloadPath.getAbsolutePath();
        }

        return null;
    }


    public interface onDownloadListener {

        /**
         * 下载成功
         */
        void onSuccess(String msg);

        /**
         * 正在下载中
         *
         * @param progress
         */
        void onDownloading(int progress);

        /**
         * 下载失败
         */
        void onFailed(String msg);
    }
}
