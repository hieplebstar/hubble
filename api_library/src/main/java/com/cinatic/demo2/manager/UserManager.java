package com.cinatic.demo2.manager;

import com.cinatic.demo2.ServiceGenerator;
import com.cinatic.demo2.endpoints.UserEndpoint;
import com.cinatic.demo2.handlers.ResponseWrapperCallBackHandler;
import com.cinatic.demo2.manager.base.BaseManager;
import com.cinatic.demo2.manager.base.BaseResponseReceivedListener;
import com.cinatic.demo2.models.AuthenticationDTO;
import com.cinatic.demo2.models.RefreshTokenDTO;
import com.cinatic.demo2.models.RegisterDTO;
import com.cinatic.demo2.models.ResetPasswordDTO;
import com.cinatic.demo2.models.UpdatePasswordDTO;
import com.cinatic.demo2.models.responses.AuthenticationToken;
import com.cinatic.demo2.models.responses.WrapperResponse;
import com.cinatic.demo2.models.responses.RegisterResponse;
import com.cinatic.demo2.models.responses.UserInfoResponse;

import okhttp3.ResponseBody;
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
    public interface OnRefreshTokenListener extends BaseResponseReceivedListener<AuthenticationToken> {}
    public interface OnGetUserInfoListener extends BaseResponseReceivedListener<UserInfoResponse> {}
    public interface OnUpdatePasswordListener extends BaseResponseReceivedListener<ResponseBody> {}
    public interface OnResetPasswordListener extends BaseResponseReceivedListener<ResponseBody> {}

    public void register(String userName, String email, String password, String confirmPassword, OnRegisterListener listener) {
        Call<WrapperResponse<RegisterResponse>> call = getService().register(new RegisterDTO(userName, email, password, confirmPassword, ""));
        call.enqueue(new ResponseWrapperCallBackHandler<>(listener));
    }

    public void authenticate(String userName, String password, int oauthType, String oauthToken, final OnAuthenticateListener listener) {
        Call<WrapperResponse<AuthenticationToken>> call = getService().authenticate(new AuthenticationDTO(ServiceGenerator.getOs(), "unknown", oauthType, false, userName, password));
        call.enqueue(new ResponseWrapperCallBackHandler<>(new OnAuthenticateListener() {
            @Override
            public void onFailure(Throwable error) {
                listener.onFailure(error);
            }

            @Override
            public void onSuccess(AuthenticationToken result) {
                ServiceGenerator.setAccessToken(result.getAccessToken());
                ServiceGenerator.setRefreshToken(result.getRefreshToken());
                listener.onSuccess(result);
            }
        }));
    }

    public void refreshToken(final OnRefreshTokenListener listener) {
        Call<WrapperResponse<AuthenticationToken>> call = getService().refreshToken(new RefreshTokenDTO(ServiceGenerator.getRefreshToken()));
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
        Call<WrapperResponse<UserInfoResponse>> call = getService().getUserInfo(ServiceGenerator.getAccessToken());
        call.enqueue(new ResponseWrapperCallBackHandler<>(listener));
    }

    public void updatePassword(String currentPassword, String password, String confirmPassword, OnUpdatePasswordListener listener) {
        Call<WrapperResponse<ResponseBody>> call = getService().updatePassword(new UpdatePasswordDTO(currentPassword, password, confirmPassword));
        call.enqueue(new ResponseWrapperCallBackHandler<>(listener));
    }

    public void resetPassword(String userName, String email, OnResetPasswordListener listener) {
        Call<WrapperResponse<ResponseBody>> call = getService().resetPassword(new ResetPasswordDTO(userName, email));
        call.enqueue(new ResponseWrapperCallBackHandler<>(listener));
    }
}
