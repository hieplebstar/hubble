package com.cinatic.demo2.plugincontroller;

import com.android.appkit.controller.EventPluginController;
import com.cinatic.demo2.events.UserDoLoadInfoEvent;
import com.cinatic.demo2.events.UserDoLoadInfoReturnEvent;
import com.cinatic.demo2.events.UserDoLoginEvent;
import com.cinatic.demo2.events.UserDoLoginReturnEvent;
import com.cinatic.demo2.events.UserDoRegisterEvent;
import com.cinatic.demo2.events.UserDoRegisterReturnedEvent;
import com.cinatic.demo2.events.UserDoResetPasswordEvent;
import com.cinatic.demo2.events.UserDoResetPasswordReturnEvent;
import com.cinatic.demo2.events.show.ShowFeedbackMessageEvent;
import com.cinatic.demo2.manager.UserManager;
import com.cinatic.demo2.models.responses.AuthenticationToken;
import com.cinatic.demo2.models.responses.RegisterResponse;
import com.cinatic.demo2.models.responses.UserInfo;

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
            post(new UserDoResetPasswordReturnEvent());
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
    public void onEvent(UserDoResetPasswordEvent event){
        mUserManager.resetPassword(event.getUserName(), event.getEmail(), onResetPasswordListener);
    }

    @Subscribe
    public void onEvent(UserDoLoadInfoEvent event){
        mUserManager.getUserInfo(onGetUserInfoListener);
    }

    private void handleError(Throwable throwable){
        post(new ShowFeedbackMessageEvent(throwable.getMessage()));
    }
}
