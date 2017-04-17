package com.cinatic.demo2.plugincontroller;

import com.android.appkit.controller.EventPluginController;
import com.cinatic.demo2.events.UserDoChangePasswordEvent;
import com.cinatic.demo2.events.UserDoChangePasswordReturnEvent;
import com.cinatic.demo2.events.UserDoLoadInfoEvent;
import com.cinatic.demo2.events.UserDoLoadInfoReturnEvent;
import com.cinatic.demo2.events.UserDoLoginEvent;
import com.cinatic.demo2.events.UserDoLoginReturnEvent;
import com.cinatic.demo2.events.UserDoRefreshTokenEvent;
import com.cinatic.demo2.events.UserDoRefreshTokenReturnEvent;
import com.cinatic.demo2.events.UserDoRegisterEvent;
import com.cinatic.demo2.events.UserDoRegisterReturnedEvent;
import com.cinatic.demo2.events.UserDoResetPasswordEvent;
import com.cinatic.demo2.events.UserDoResetPasswordReturnEvent;
import com.cinatic.demo2.events.show.ShowFeedbackMessageEvent;
import com.cinatic.demo2.manager.UserManager;
import com.cinatic.demo2.models.responses.AuthenticationToken;
import com.cinatic.demo2.models.responses.RegisterResponse;
import com.cinatic.demo2.models.responses.UpdatePasswordResponse;
import com.cinatic.demo2.models.responses.UserInfo;
import com.cinatic.demo2.persistances.SettingPreferences;

import org.greenrobot.eventbus.Subscribe;

import okhttp3.ResponseBody;

/**
 * Created by HiepLe on 4/5/2017.
 */

public class UserPluginController extends EventPluginController{

    private UserManager mUserManager;

    public UserPluginController(){
        mUserManager = new UserManager();
    }

    UserManager.OnAuthenticateListener onAuthenticateListener = new UserManager.OnAuthenticateListener() {
        @Override
        public void onFailure(Throwable error) {
            handleError(error);
        }

        @Override
        public void onSuccess(AuthenticationToken result) {
            new SettingPreferences().putRefreshToken(result.getRefreshToken());
            post(new UserDoLoginReturnEvent());
        }
    };

    UserManager.OnRegisterListener onRegisterListener = new UserManager.OnRegisterListener() {
        @Override
        public void onFailure(Throwable error) {
            handleError(error);
        }

        @Override
        public void onSuccess(RegisterResponse result) {
            post(new UserDoRegisterReturnedEvent(result));
        }
    };

    UserManager.OnRefreshTokenListener onRefreshTokenListener = new UserManager.OnRefreshTokenListener() {
        @Override
        public void onFailure(Throwable error) {
            handleError(error);
        }

        @Override
        public void onSuccess(AuthenticationToken result) {
            post(new UserDoRefreshTokenReturnEvent());
        }
    };


    UserManager.OnGetUserInfoListener onGetUserInfoListener = new UserManager.OnGetUserInfoListener() {
        @Override
        public void onFailure(Throwable error) {
            handleError(error);
        }

        @Override
        public void onSuccess(UserInfo result) {
            postSticky(new UserDoLoadInfoReturnEvent(result));
        }
    };

    UserManager.OnResetPasswordListener onResetPasswordListener = new UserManager.OnResetPasswordListener() {
        @Override
        public void onFailure(Throwable error) {
            handleError(error);
        }

        @Override
        public void onSuccess(ResponseBody result) {
            post(new UserDoChangePasswordReturnEvent());
        }
    };

    UserManager.OnUpdatePasswordListener onUpdatePasswordListener = new UserManager.OnUpdatePasswordListener() {
        @Override
        public void onFailure(Throwable error) {
            handleError(error);
        }

        @Override
        public void onSuccess(UpdatePasswordResponse result) {
            post(new UserDoChangePasswordReturnEvent());
        }
    };

    @Subscribe
    public void onEvent(UserDoLoginEvent event) {
        mUserManager.authenticate(event.getUserName(), event.getPassword(), event.getOauthType(), event.getOauthToken(), onAuthenticateListener);
    }

    @Subscribe
    public void onEvent(UserDoRegisterEvent event){
        mUserManager.register(event.getUsername(), event.getEmail(), event.getPassword(), event.getConfirmPassword(), onRegisterListener);
    }

    @Subscribe
    public void onEvent(UserDoRefreshTokenEvent event){
        mUserManager.refreshToken(event.getRefreshToken(), onRefreshTokenListener);
    }

    @Subscribe
    public void onEvent(UserDoResetPasswordEvent event){
        mUserManager.resetPassword(event.getUserName(), event.getEmail(), onResetPasswordListener);
    }

    @Subscribe
    public void onEvent(UserDoChangePasswordEvent event){
        mUserManager.updatePassword(event.getOldPass(), event.getNewPass(), event.getConfirmPass(), onUpdatePasswordListener);
    }

    @Subscribe
    public void onEvent(UserDoLoadInfoEvent event){
        mUserManager.getUserInfo(onGetUserInfoListener);
    }

    private void handleError(Throwable throwable){
        post(new ShowFeedbackMessageEvent(throwable.getMessage()));
    }
}
