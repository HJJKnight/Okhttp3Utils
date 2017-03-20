package com.example.knight.libraryutils.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 缓存类型
 * Created by knight on 2017/3/20.
 */

@IntDef({CacheType.FORCE_NETWORK, CacheType.FORCE_CACHE, CacheType.NETWORK_THEN_CANCHE, CacheType.CACHE_THEN_NETWIORK})
@Retention(RetentionPolicy.SOURCE)
public @interface CacheType {

    int FORCE_NETWORK = 1;
    int FORCE_CACHE = 2;
    int NETWORK_THEN_CANCHE = 3;
    int CACHE_THEN_NETWIORK = 4;
}
