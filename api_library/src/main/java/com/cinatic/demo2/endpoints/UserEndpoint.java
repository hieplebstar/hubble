package com.cinatic.demo2.endpoints;


import com.cinatic.demo2.models.AuthenticationDTO;
import com.cinatic.demo2.models.RegisterDTO;
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
    Call<RegisterResponse> register(@Body RegisterDTO registerDTO);

    @POST("users/authenticate")
    @Headers("Content-Type:application/json")
    Call<AuthenticationToken> authenticate(@Body AuthenticationDTO authenticationDTO);

}
