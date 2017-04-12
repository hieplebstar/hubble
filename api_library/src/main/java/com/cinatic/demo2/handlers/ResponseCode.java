package com.cinatic.demo2.handlers;

/**
 * Created by HiepLe on 10/10/2016.
 */

public enum ResponseCode {
    SUCCESSFUL(201),
    CREATED(201),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOTFOUND(404);
    private final int value;

    private ResponseCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
