package com.cinatic.demo2.manager;

import com.cinatic.demo2.ServiceGenerator;
import com.cinatic.demo2.endpoints.DeviceEventEndpoint;
import com.cinatic.demo2.handlers.ResponseWrapperCallBackHandler;
import com.cinatic.demo2.manager.base.BaseManager;
import com.cinatic.demo2.manager.base.BaseResponseReceivedListener;
import com.cinatic.demo2.models.responses.DeviceEvent;
import com.cinatic.demo2.models.responses.DeviceEventResponse;
import com.cinatic.demo2.models.responses.WrapperResponse;

import java.util.List;

import retrofit2.Call;

/**
 * Created by HiepLe on 2/1/17.
 */

public class DeviceEventManager extends BaseManager<DeviceEventEndpoint> {
    public DeviceEventManager() {
        super(DeviceEventEndpoint.class);
    }

    public interface OnGetListDeviceEventListener extends BaseResponseReceivedListener<DeviceEventResponse> {
    }

    public interface OnGetDeviceEventDetailListener extends BaseResponseReceivedListener<DeviceEvent> {
    }

    public void getListDeviceEvent(String deviceIds, int page, OnGetListDeviceEventListener listener) {
        Call<WrapperResponse<DeviceEventResponse>> call = getService().getListDeviceEvent(deviceIds, page, ServiceGenerator.getAccessToken());
        call.enqueue(new ResponseWrapperCallBackHandler<>(listener));
    }

    public void getDeviceEventDetail(String deviceId, String eventId, OnGetDeviceEventDetailListener listener) {
        Call<WrapperResponse<DeviceEvent>> call = getService().getDeviceEventDetail(deviceId, eventId, ServiceGenerator.getAccessToken());
        call.enqueue(new ResponseWrapperCallBackHandler<>(listener));
    }
}
