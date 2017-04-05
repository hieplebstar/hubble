package com.cinatic.demo2.fragments.bottomtab;
import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.events.show.ShowHomeDeviceEvent;

/**
 * Created by HiepLe on 8/12/2016.
 */
public class BottomTabPresenter extends EventListeningPresenter<BottomTabView> {

    public static final int CAMERA_TAB = 0;
    public static final int VIDEO_TAB = 1;
    public static final int NOTIFICATION_TAB = 2;
    public static final int TIMETABLE_TAB = 3;

    @Override
    public void start(BottomTabView bottomTabView) {
        super.start(bottomTabView);
    }

    public void bottomTabSelected(int position) {
        switch (position){
            case CAMERA_TAB:
                post(new ShowHomeDeviceEvent());
                break;
            case VIDEO_TAB:
                break;
            case NOTIFICATION_TAB:
                break;
            case TIMETABLE_TAB:
                break;
        }
    }
}
