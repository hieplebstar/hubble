package com.cinatic.demo2.manager.base;

/**
 * Created by HiepLe on 10/10/2016.
 */

public interface BaseResponseReceivedListener<T> {
    void onFailure(Throwable error);

    void onSuccess(T result);
}
