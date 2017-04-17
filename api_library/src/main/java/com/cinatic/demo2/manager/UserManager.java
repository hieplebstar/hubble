package com.cinatic.demo2.manager;

import com.cinatic.demo2.ServiceGenerator;
import com.cinatic.demo2.endpoints.UserEndpoint;
import com.cinatic.demo2.handlers.ResponseWrapperCallBackHandler;
import com.cinatic.demo2.manager.base.BaseManager;
import com.cinatic.demo2.manager.base.BaseResponseReceivedListener;
import com.cinatic.demo2.models.AuthenticationDTO;
import com.cinatic.demo2.models.RegisterDTO;
import com.cinatic.demo2.models.ResetPasswordDTO;
import com.cinatic.demo2.models.UpdatePasswordDTO;
import com.cinatic.demo2.models.responses.AuthenticationToken;
import com.cinatic.demo2.models.responses.RegisterResponse;
import com.cinatic.demo2.models.responses.UpdatePasswordResponse;
import com.cinatic.demo2.models.responses.UserInfo;
import com.cinatic.demo2.models.responses.WrapperResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by HiepLe on 2/1/17.
 */

public class UserManager extends BaseManager<UserEndpoint> {
    public UserManager() {
        super(UserEndpoint.class);
    }

    public interface OnAuthenticateListener extends BaseResponseReceivedListener<AuthenticationToken> {
    }

    public interface OnRegisterListener extends BaseResponseReceivedListener<RegisterResponse> {
    }

    public interface OnRefreshTokenListener extends BaseResponseReceivedListener<AuthenticationToken> {
    }

    public interface OnGetUserInfoListener extends BaseResponseReceivedListener<UserInfo> {
    }

    public interface OnUpdatePasswordListener extends BaseResponseReceivedListener<UpdatePasswordResponse> {
    }

    public interface OnResetPasswordListener extends BaseResponseReceivedListener<ResponseBody> {
    }

    public void register(String userName, String email, String password, String confirmPassword, OnRegisterListener listener) {
        Call<WrapperResponse<RegisterResponse>> call = getService().register(new RegisterDTO(userName, email, password, confirmPassword, ""));
        call.enqueue(new ResponseWrapperCallBackHandler<>(listener));
    }

    public void authenticate(String userName, String password, int oauthType, String oauthToken, final OnAuthenticateListener listener) {
        Call<WrapperResponse<AuthenticationToken>> call = getService().authenticate(new AuthenticationDTO(ServiceGenerator.getOs(), "unknown", oauthType, true, userName, password));
        call.enqueue(new ResponseWrapperCallBackHandler<>(new OnAuthenticateListener() {
            @Override
            public void onFailure(Throwable error) {
                listener.onFailure(error);
            }

            @Override
            public void onSuccess(AuthenticationToken result) {
                ServiceGenerator.setAccessToken(result.getAccessToken());
                listener.onSuccess(result);
            }
        }));
    }

    public void refreshToken(String refreshToken, final OnRefreshTokenListener listener) {
        Call<WrapperResponse<AuthenticationToken>> call = getService().refreshToken(refreshToken);
        call.enqueue(new ResponseWrapperCallBackHandler<>(new OnAuthenticateListener() {
            @Override
            public void onFailure(Throwable error) {
                listener.onFailure(error);
            }

            @Override
            public void onSuccess(AuthenticationToken result) {
                ServiceGenerator.setAccessToken(result.getAccessToken());
                listener.onSuccess(result);
            }
        }));
    }

    public void getUserInfo(OnGetUserInfoListener listener) {
        Call<WrapperResponse<UserInfo>> call = getService().getUserInfo(ServiceGenerator.getAccessToken());
        call.enqueue(new ResponseWrapperCallBackHandler<>(listener));
    }

    public void updatePassword(String currentPassword, String password, String confirmPassword, OnUpdatePasswordListener listener) {
        Call<WrapperResponse<UpdatePasswordResponse>> call = getService().updatePassword(ServiceGenerator.getAccessToken(), new UpdatePasswordDTO(currentPassword, password, confirmPassword));
        call.enqueue(new ResponseWrapperCallBackHandler<>(listener));
    }

    public void resetPassword(String userName, String email, OnResetPasswordListener listener) {
        Call<WrapperResponse<ResponseBody>> call = getService().resetPassword(new ResetPasswordDTO(userName, email));
        call.enqueue(new ResponseWrapperCallBackHandler<>(listener));
    }
}
