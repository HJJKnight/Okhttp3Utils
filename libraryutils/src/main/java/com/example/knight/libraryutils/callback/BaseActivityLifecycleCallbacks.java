package com.example.knight.libraryutils.callback;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Call;

/**
 * Activity 生命周期回调
 * Created by knight on 2017/3/20.
 */

public class BaseActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = "ActivityLifecycle";

    //是否显示 ActivityLifeCircle 日志
    private static boolean isShowLifeCircle;

    //请求集合： key=Activity  value=Call 集合
    private static Map<String, SparseArray<Call>> callsMap = new ConcurrentHashMap<>();

    /**
     * 添加请求集合
     *
     * @param tag  请求标志
     * @param call 请求体
     */
    public static void putCall(String tag, Call call) {
        if (tag != null) {
            SparseArray<Call> callArray = callsMap.get(tag);
            if (callArray == null) {
                callArray = new SparseArray<>();
            }
            callArray.put(call.hashCode(), call);
            callsMap.put(tag, callArray);
            showLog(false, tag);
        }
    }

    /**
     * 在Activity 的生命周期结束的时候将请求全部取消
     *
     * @param tag 当前 Activity 的标志
     */
    public static void cancelCallByActivityDestroy(String tag) {
        if (tag == null) {
            return;
        }
        SparseArray<Call> callArray = callsMap.get(tag);
        if (callArray != null) {
            int len = callArray.size();
            for (int i = 0; i < len; i++) {
                Call call = callArray.get(i);
                if (call != null && !call.isCanceled()) {
                    call.cancel();
                }
            }
            callArray.clear();
            callsMap.remove(tag);
            showLog(true, tag);
        }
    }

    /**
     * 取消tag 下的全部请求
     *
     * @param tag
     */
    public static void cancel(String tag) {
        cancel(tag, null);
    }

    /**
     * 取消tag 标志下的某一个具体请求
     *
     * @param tag
     * @param originnalCall
     */
    public static void cancel(String tag, Call originnalCall) {
        if (TextUtils.isEmpty(tag)) {
            return;
        }
        SparseArray<Call> callArray = callsMap.get(tag);

        if (callArray != null) {
            if (originnalCall != null) {

                Call call = callArray.get(originnalCall.hashCode());
                if (call != null && !call.isCanceled()) {
                    call.cancel();
                }
                callArray.remove(originnalCall.hashCode());
                if (callArray.size() == 0) {
                    callsMap.remove(tag);
                }
                showLog(true, tag);

            } else {
                for (int i = 0; i < callArray.size(); i++) {
                    Call call = callArray.get(i);
                    if (call != null && !call.isCanceled()) {
                        call.cancel();
                        callArray.delete(call.hashCode());
                    }
                    if (callArray.size() == 0) {
                        callsMap.remove(tag);
                    }
                    showLog(true, tag);
                }
            }
        }
    }

    private static void showLog(boolean isCancel, String tag) {
        if (!isShowLifeCircle) {
            return;
        }
        String callDes = isCancel ? "取消请求" : "添加请求";
        Log.d(TAG, tag + ":" + callDes);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        cancelCallByActivityDestroy(activity.getClass().getName());
    }

    /**
     * 是否显示Log
     * @param showLifeCircleLog
     */
    public static void setShowLifeCircleLog(boolean showLifeCircleLog){
        isShowLifeCircle = showLifeCircleLog;
    }
}
