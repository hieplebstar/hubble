package com.cinatic.demo2.endpoints;

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
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by HiepLe on 2/1/17.
 */

public interface UserEndpoint {

    @POST("users/account")
    Call<WrapperResponse<RegisterResponse>> register(@Body RegisterDTO registerDTO);

    @POST("users/authenticate")
    Call<WrapperResponse<AuthenticationToken>> authenticate(@Body AuthenticationDTO authenticationDTO);

    @GET("users/account")
    Call<WrapperResponse<UserInfo>> getUserInfo(@Query("access_token") String accessToken);

    @GET("users/authenticate")
    Call<WrapperResponse<AuthenticationToken>> refreshToken(@Query("refresh_token") String refreshToken);

    @POST("users/account/recover_password")
    Call<WrapperResponse<ResponseBody>> resetPassword(@Body ResetPasswordDTO resetPasswordDTO);

    @PUT("users/account/change_password")
    Call<WrapperResponse<UpdatePasswordResponse>> updatePassword(@Query("access_token") String accessToken,
                                                                 @Body UpdatePasswordDTO updatePasswordDTO);

}
