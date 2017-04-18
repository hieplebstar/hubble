package com.cinatic.demo2.fragments.homeevent;


import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.events.DeviceListDoLoadEvent;
import com.cinatic.demo2.events.DeviceListDoReturnEvent;
import com.cinatic.demo2.events.EventListDoLoadEvent;
import com.cinatic.demo2.events.EventListDoReturnEvent;
import com.cinatic.demo2.events.show.ShowDeviceActivatedEvent;
import com.cinatic.demo2.events.show.ShowDeviceInnerEvent;
import com.cinatic.demo2.events.show.ShowSetupWelcomeEvent;
import com.cinatic.demo2.fragments.homedevice.DevicesView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class EventPresenter extends EventListeningPresenter<EventView> {

    private final int FIRST_PAGE = 1;
    private int page;
    private String deviceIds;

    EventPresenter(){
        page = FIRST_PAGE;
        deviceIds = "";
    }

    @Subscribe
    public void onEvent(EventListDoReturnEvent event) {
        view.showLoading(false);
        view.showEventList(event.getEventList());
        if(page == event.getTotalPages()) {
            page = -1;
            return;
        }
        page = page + 1;
    }

    public void loadEventList(){
        if (page == -1){
            return;
        }
        view.showLoading(true);
        post(new EventListDoLoadEvent(deviceIds, page));
    }

    public void showDetail(String id, String name, String deviceId){
        post(new ShowDeviceInnerEvent(id, name, deviceId));
    }
}
