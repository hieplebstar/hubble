package com.cinatic.demo2.manager;

import com.cinatic.demo2.AppEnvironmentManager;
import com.cinatic.demo2.endpoints.UserEndpoint;
import com.cinatic.demo2.handlers.ResponseCallBackHandler;
import com.cinatic.demo2.manager.base.BaseManager;
import com.cinatic.demo2.manager.base.BaseResponseReceivedListener;
import com.cinatic.demo2.models.AuthenticationDTO;
import com.cinatic.demo2.models.responses.AuthenticationToken;
import com.cinatic.demo2.models.responses.RegisterResponse;

import retrofit2.Call;

/**
 * Created by HiepLe on 2/1/17.
 */

public class UserManager extends BaseManager<UserEndpoint> {
    public UserManager() {
        super(UserEndpoint.class);
    }
    public interface OnAuthenticateListener extends BaseResponseReceivedListener<AuthenticationToken> {}
    public interface OnRegisterListener extends BaseResponseReceivedListener<RegisterResponse> {}

    public void register(String userName, String email, String password, String confirmPassword, OnRegisterListener listener) {
        Call<RegisterResponse> call = getService().register(userName, email, password, confirmPassword, "");
        call.enqueue(new ResponseCallBackHandler<>(listener));
    }

    public void authenticate(String userName, String password, String oauthType, String oauthToken, OnAuthenticateListener listener) {
        Call<AuthenticationToken> call = getService().authenticate(new AuthenticationDTO(AppEnvironmentManager.getAPIEnvironment().getOs(), "unknown", oauthType, oauthToken, false, userName, password));
        call.enqueue(new ResponseCallBackHandler<>(listener));
    }
}
