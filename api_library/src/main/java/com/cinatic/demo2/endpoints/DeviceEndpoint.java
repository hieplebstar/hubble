package com.cinatic.demo2.endpoints;

import com.cinatic.demo2.models.UpdateDeviceDTO;
import com.cinatic.demo2.models.responses.AuthenticationToken;
import com.cinatic.demo2.models.responses.Device;
import com.cinatic.demo2.models.responses.RegisterResponse;
import com.cinatic.demo2.models.responses.UserInfo;
import com.cinatic.demo2.models.responses.WrapperResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by HiepLe on 2/1/17.
 */

public interface DeviceEndpoint {

    @GET("users/devices")
    Call<WrapperResponse<List<Device>>> getListUserDevices(@Query("access_token") String accessToken);

    @GET("users/devices/{device_id}")
    Call<WrapperResponse<Device>> getDevice(@Path("device_id") String device, @Query("access_token") String accessToken);

    @DELETE("users/devices/{device_id}")
    Call<WrapperResponse<ResponseBody>> removeDevice(@Path("device_id") String device, @Query("access_token") String accessToken);

    @PUT("users/devices/{device_id}")
    Call<WrapperResponse<ResponseBody>> updateDevice(@Path("device_id") String device,
                                                     @Query("access_token") String accessToken,
                                                     @Body UpdateDeviceDTO updateDeviceDTO);

}
