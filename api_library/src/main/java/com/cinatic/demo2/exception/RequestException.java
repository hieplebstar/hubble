package com.cinatic.demo2.exception;

import com.cinatic.demo2.models.responses.ErrorResponse;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

/**
 * Created by HiepLe on 10/10/2016.
 */

public class RequestException extends Exception {
    public RequestException(String detailMessage) {
        super(detailMessage);
    }

    public RequestException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public static RequestException parseError(String jsonString) {
        try {
            ErrorResponse errorResponse = new Gson().fromJson(jsonString, ErrorResponse.class);
            return new RequestException(errorResponse.getMessage());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        }
        return new RequestException("connection error");
    }
}
