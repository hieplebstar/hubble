package com.cinatic.demo2.manager.base;


import com.cinatic.demo2.ServiceGenerator;

/**
 * Created by HiepLeon 6/29/16.
 */
public abstract class BaseManager<T> {

    private Class<T> endpointClazz;
    public BaseManager(Class<T> endpointClazz) {
        this.endpointClazz = endpointClazz;
    }

    protected T getService() {
        return ServiceGenerator.createService(endpointClazz);
    }
}
