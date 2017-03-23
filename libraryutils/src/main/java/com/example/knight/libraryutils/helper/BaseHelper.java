package com.example.knight.libraryutils.helper;

import android.util.Log;

import com.example.knight.libraryutils.HttpInfo;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 业务操作基类：日志拦截与打印、Https验证
 * Created by knight on 2017/3/20.
 */

public class BaseHelper {

    OkHttpClient httpClient;
    protected String tag;
    protected String timeStamp;
    protected boolean showHttpLog;
    //请求标志
    protected String requestTag;


    BaseHelper() {
    }

    BaseHelper(HelperInfo info) {
        tag = info.getLogTAG();
        timeStamp = info.getTimeStamp();
        showHttpLog = info.isShowHttpLog();
        requestTag = info.getRequestTag();
        OkHttpClient defualtClient = info.getOkHttpUtil().getDefaultClient();
        if (info.isDefault()) {
            if (defualtClient == null) {
                httpClient = initHttpClient(info, null);
                info.getOkHttpUtil().setDefaultClient(httpClient);
            } else {
                httpClient = initHttpClient(info, defualtClient.cookieJar());
            }
        } else {
            httpClient = initHttpClient(info, null);
        }
    }

    /**
     * 获取一个Okhttpclient 对象，并设置相关内容
     *
     * @param helperInfo
     * @param cookieJar
     * @return
     */
    private OkHttpClient initHttpClient(HelperInfo helperInfo, CookieJar cookieJar) {
        OkHttpClient.Builder clientBuilder = helperInfo.getClientBuilder();
        clientBuilder.protocols(Arrays.asList(Protocol.HTTP_1_1, Protocol.HTTP_2));
        clientBuilder.addInterceptor(LOG_INTERCEPTOR);
        if (cookieJar != null) {
            //设置可以接受HTTP响应的Cookie处理，并提供Cookie输出HTTP请求
            clientBuilder.cookieJar(cookieJar);
        }
        setSslSocketFactory(clientBuilder);
        return clientBuilder.build();
    }

    /**
     * 设置 HTTPS 认证
     *
     * @param clientBuilder
     */
    private void setSslSocketFactory(OkHttpClient.Builder clientBuilder) {
        clientBuilder.hostnameVerifier(DO_NOT_VERIFY);
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };

            sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
            clientBuilder.sslSocketFactory(sslContext.getSocketFactory(), trustManager);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            showLog("Https 认证异常:" + e.getMessage());
        }
    }

    /**
     * 日志拦截器
     */
    private final Interceptor LOG_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            long startTime = System.currentTimeMillis();

            showLog(String.format("%s-URL: %s %n", chain.request().method(), chain.request().url()));
            Response result = chain.proceed(originalRequest);

            long endTime = System.currentTimeMillis();
            showLog(String.format("CostTime: %.1fs", ((endTime - startTime) / 1000f)));
            return result;
        }
    };

    /**
     * 主机名验证
     */
    private final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * 打印日志
     *
     * @param msg
     */
    private void showLog(String msg) {
        if (showHttpLog) {
            Log.d(tag + "[" + timeStamp + "]", msg);
        }
    }


}
