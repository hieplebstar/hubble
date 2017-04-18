package com.cinatic.demo2.endpoints;

import com.cinatic.demo2.models.UpdateDeviceDTO;
import com.cinatic.demo2.models.responses.Device;
import com.cinatic.demo2.models.responses.DeviceEvent;
import com.cinatic.demo2.models.responses.DeviceEventResponse;
import com.cinatic.demo2.models.responses.WrapperResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by HiepLe on 2/1/17.
 */

public interface DeviceEventEndpoint {

    @GET("devices/events/all")
    Call<WrapperResponse<DeviceEventResponse>> getListDeviceEvent(@Query("device_ids") String deviceIds,
                                                                        @Query("page") int page,
                                                                        @Query("access_token") String accessToken);

    @GET("devices/events/{device_id}/{event_id}")
    Call<WrapperResponse<DeviceEvent>> getDeviceEventDetail(@Path("device_id") String deviceId,
                                                       @Path("event_id") String eventId,
                                                       @Query("access_token") String accessToken);

}
