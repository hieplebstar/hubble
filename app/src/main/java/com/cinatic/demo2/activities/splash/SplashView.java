package com.cinatic.demo2.activities.splash;

/**
 * Created by HiepLe on 7/6/2016.
 */
public interface SplashView {
    void directToMainActivity();
    void directToLoginActivity();
    void showLoading(boolean isLoading);
    void showSnackBar(String message);
}
