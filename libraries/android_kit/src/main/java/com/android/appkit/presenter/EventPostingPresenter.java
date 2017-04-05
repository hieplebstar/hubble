package com.android.appkit.presenter;


import com.android.appkit.AndroidApplication;
import com.android.appkit.eventbus.EventBusController;

public abstract class EventPostingPresenter {

    public void post(Object event) {
        AndroidApplication.getEvenBusController().postEvent(event);
    }

    protected void postDelay(Object event, long delay) {
        AndroidApplication.getEvenBusController().postEventDelayed(event, delay);
    }

    protected void postSticky(Object event) {
        AndroidApplication.getEvenBusController().postStickyEvent(event);
    }

    protected <T> T removeSticky(Class<T> eventClass) {
        return AndroidApplication.getEvenBusController().removeSticky(eventClass);
    }

    protected <T> T getSticky(Class<T> eventClass) {
        return AndroidApplication.getEvenBusController().getSticky(eventClass);
    }
}
