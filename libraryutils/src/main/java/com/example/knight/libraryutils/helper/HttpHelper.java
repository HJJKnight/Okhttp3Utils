package com.example.knight.libraryutils.helper;

import com.example.knight.libraryutils.HttpInfo;
import com.example.knight.libraryutils.interceptor.ExceptionInterceptor;
import com.example.knight.libraryutils.interceptor.ResultInterceptor;

import java.util.List;

/**
 * Created by knight on 2017/3/23.
 */

public class HttpHelper extends BaseHelper{

    private List<ResultInterceptor> resultInterceptors;
    private List<ExceptionInterceptor> exceptionInterceptors;

    HttpHelper(HelperInfo info){
        super(info);
        resultInterceptors = info.getResultInterceptors();
        exceptionInterceptors = info.getExceptionInterceptors();
    }

    /**
     * 同步请求
     * @return
     */
    HttpInfo doRequestSync(OkHttpHelper okHttpHelper){

    }
}
