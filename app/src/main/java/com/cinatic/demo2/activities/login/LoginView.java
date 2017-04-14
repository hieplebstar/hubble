package com.cinatic.demo2.activities.login;

/**
 * Created by HiepLe on 7/6/2016.
 */
public interface LoginView {
    void directToMainActivity();
    void showLoading(boolean isLoading);
    void showSnackBar(String message);
}
