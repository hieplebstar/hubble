package com.cinatic.demo2.handlers;


import com.cinatic.demo2.exception.RequestException;
import com.cinatic.demo2.manager.base.BaseResponseReceivedListener;
import com.cinatic.demo2.models.responses.WrapperResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HiepLe on 10/10/2016.
 */

public  class ResponseWrapperCallBackHandler<T> implements Callback<WrapperResponse<T>> {
    BaseResponseReceivedListener responseReceivedListener;

    public ResponseWrapperCallBackHandler(BaseResponseReceivedListener<T> responseReceivedListener) {
        this.responseReceivedListener = responseReceivedListener;
    }

    @Override
    public void onResponse(Call<WrapperResponse<T>> call, Response<WrapperResponse<T>> response) {
        if (response.isSuccessful()){
            T res = response.body().getData();
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
    public void onFailure(Call<WrapperResponse<T>> call, Throwable t) {
        responseReceivedListener.onFailure(t);
    }
}
