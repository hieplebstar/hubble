package com.cinatic.demo2.handlers;


import com.cinatic.demo2.exception.RequestException;
import com.cinatic.demo2.manager.base.BaseResponseReceivedListener;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HiepLe on 10/10/2016.
 */

public  class ResponseCallBackHandler<T> implements Callback<T> {
    BaseResponseReceivedListener responseReceivedListener;

    public ResponseCallBackHandler(BaseResponseReceivedListener<T> responseReceivedListener) {
        this.responseReceivedListener = responseReceivedListener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()){
            T res = response.body();
            responseReceivedListener.onSuccess(res);
        } else {
            try {
                String errorString = response.errorBody().string();
                responseReceivedListener.onFailure(RequestException.parseError(errorString));
                return;
            } catch (IOException e) {
                e.printStackTrace();
                responseReceivedListener.onFailure(new RequestException("Connection error"));
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        responseReceivedListener.onFailure(t);
    }
}
