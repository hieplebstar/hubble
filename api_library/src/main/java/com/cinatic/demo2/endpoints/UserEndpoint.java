package com.cinatic.demo2.endpoints;


import com.cinatic.demo2.models.AuthenticationDTO;
import com.cinatic.demo2.models.responses.AuthenticationToken;
import com.cinatic.demo2.models.responses.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by HiepLe on 2/1/17.
 */

public interface UserEndpoint {

    @POST("users/account")
    Call<RegisterResponse> register(@Field("user_name") String user_name,
                                    @Field("email") String email,
                                    @Field("password") String password,
                                    @Field("confirm_password") String confirm_password,
                                    @Field("secure_code") String secure_code);

    @POST("users/authenticate")
    @Headers("Content-Type:application/json")
    Call<AuthenticationToken> authenticate(@Body AuthenticationDTO authenticationDTO);

}
