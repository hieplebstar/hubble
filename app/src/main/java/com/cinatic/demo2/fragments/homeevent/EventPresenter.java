package com.cinatic.demo2.fragments.homeevent;


import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.events.DeviceListDoLoadEvent;
import com.cinatic.demo2.events.DeviceListDoReturnEvent;
import com.cinatic.demo2.events.EventListDoLoadEvent;
import com.cinatic.demo2.events.EventListDoReturnEvent;
import com.cinatic.demo2.events.show.ShowDeviceActivatedEvent;
import com.cinatic.demo2.events.show.ShowDeviceInnerEvent;
import com.cinatic.demo2.events.show.ShowSetupWelcomeEvent;
import com.cinatic.demo2.events.show.ShowVideoPlayEvent;
import com.cinatic.demo2.fragments.homedevice.DevicesView;
import com.cinatic.demo2.models.responses.DeviceEvent;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class EventPresenter extends EventListeningPresenter<EventView> {

    private final int FIRST_PAGE = 1;
    private int page;
    private String deviceIds;
    private boolean isLoading;
    private List<DeviceEvent> mEventList;

    EventPresenter(){
        page = FIRST_PAGE;
        deviceIds = "";
    }

    @Subscribe
    public void onEvent(EventListDoReturnEvent event) {
        view.showLoading(false);
        isLoading = false;
        if(page == FIRST_PAGE){
            mEventList = event.getEventList();
        } else {
            mEventList.addAll(event.getEventList());
        }
        view.showEventList(mEventList);
        if(page == event.getTotalPages()) {
            page = -1;
        } else {
            page = page + 1;
        }
    }

    public void loadEventList(){
        if(mEventList != null){
            view.showEventList(mEventList);
            return;
        }
        view.showLoading(true);
        isLoading = true;
        post(new EventListDoLoadEvent(deviceIds, page));
    }

    public void loadMoreEvent(){
        if (page == -1 || isLoading){
            return;
        }
        view.showLoading(true);
        isLoading = true;
        post(new EventListDoLoadEvent(deviceIds, page));
    }

    public void playVideo(String url){
        post(new ShowVideoPlayEvent(url));
    }
}
