package com.example.knight.libraryutils.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 请求方法
 * Created by knight on 2017/3/20.
 */
@IntDef({RequestMethod.GET,RequestMethod.POST})
@Retention(RetentionPolicy.SOURCE)
public @interface RequestMethod {
    int POST = 1;
    int GET = 2;
}
