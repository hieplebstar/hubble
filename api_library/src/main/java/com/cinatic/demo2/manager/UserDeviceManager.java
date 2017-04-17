package com.cinatic.demo2.manager;

import com.cinatic.demo2.ServiceGenerator;
import com.cinatic.demo2.endpoints.DeviceEndpoint;
import com.cinatic.demo2.endpoints.UserEndpoint;
import com.cinatic.demo2.handlers.ResponseWrapperCallBackHandler;
import com.cinatic.demo2.manager.base.BaseManager;
import com.cinatic.demo2.manager.base.BaseResponseReceivedListener;
import com.cinatic.demo2.models.AuthenticationDTO;
import com.cinatic.demo2.models.RefreshTokenDTO;
import com.cinatic.demo2.models.RegisterDTO;
import com.cinatic.demo2.models.ResetPasswordDTO;
import com.cinatic.demo2.models.UpdateDeviceDTO;
import com.cinatic.demo2.models.UpdatePasswordDTO;
import com.cinatic.demo2.models.responses.AuthenticationToken;
import com.cinatic.demo2.models.responses.Device;
import com.cinatic.demo2.models.responses.RegisterResponse;
import com.cinatic.demo2.models.responses.UserInfo;
import com.cinatic.demo2.models.responses.WrapperResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by HiepLe on 2/1/17.
 */

public class UserDeviceManager extends BaseManager<DeviceEndpoint> {
    public UserDeviceManager() {
        super(DeviceEndpoint.class);
    }
    public interface OnGetListUserDeviceListener extends BaseResponseReceivedListener<List<Device>> {}
    public interface OnGetDeviceListener extends BaseResponseReceivedListener<Device> {}
    public interface OnRemoveDeviceListener extends BaseResponseReceivedListener<ResponseBody> {}
    public interface OnUpdateDeviceListener extends BaseResponseReceivedListener<ResponseBody> {}

    public void getListUserDevices(OnGetListUserDeviceListener listener) {
        Call<WrapperResponse<List<Device>>> call = getService().getListUserDevices(ServiceGenerator.getAccessToken());
        call.enqueue(new ResponseWrapperCallBackHandler<>(listener));
    }

    public void getDevice(String deviceId, OnGetDeviceListener listener) {
        Call<WrapperResponse<Device>> call = getService().getDevice(deviceId, ServiceGenerator.getAccessToken());
        call.enqueue(new ResponseWrapperCallBackHandler<>(listener));
    }

    public void removeDevice(String deviceId, OnRemoveDeviceListener listener) {
        Call<WrapperResponse<ResponseBody>> call = getService().removeDevice(deviceId, ServiceGenerator.getAccessToken());
        call.enqueue(new ResponseWrapperCallBackHandler<>(listener));
    }

    public void updateDevice(String deviceId, String name, String version, String timeZone, String localIp, String routerSSID, String routerIp, OnUpdateDeviceListener listener) {
        Call<WrapperResponse<ResponseBody>> call = getService().updateDevice(deviceId, ServiceGenerator.getAccessToken(), new UpdateDeviceDTO(name, version, timeZone, localIp, routerSSID, routerIp));
        call.enqueue(new ResponseWrapperCallBackHandler<>(listener));
    }
}
