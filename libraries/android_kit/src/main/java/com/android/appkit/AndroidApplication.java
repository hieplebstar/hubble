package com.android.appkit;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ArrayRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.WindowManager;

import com.android.appkit.eventbus.EventBusController;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by HiepLe on 4/2/2017.
 */

public abstract class AndroidApplication extends Application{

    protected abstract EventBusController createEventBusController();

    private static AndroidApplication sInstance;
    protected EventBusController eventBusController;

    public static AndroidApplication getInstance() {
        return sInstance;
    }

    public static EventBusController getEvenBusController() {
        return sInstance.eventBusController;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        eventBusController = createEventBusController();
        eventBusController.registerDefaultSubscriber();
    }



    public static int getDimenPixelSize(@DimenRes int resId) {
        return sInstance.getResources().getDimensionPixelSize(resId);
    }

    public static String getStringResource(@StringRes int resId) {
        return sInstance.getResources().getString(resId);
    }

    public static String getStringResource(@StringRes int resId, Object... formatArgs) {
        return sInstance.getResources().getString(resId, formatArgs);
    }

    public static String[] getStringArrayResource(@ArrayRes int resId) {
        return sInstance.getResources().getStringArray(resId);
    }

    public static Drawable getDrawableResource(@DrawableRes int resId) {
        return ContextCompat.getDrawable(sInstance, resId);
    }

    public static int getIntColor(int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(sInstance, id);
        } else {
            return sInstance.getResources().getColor(id);
        }
    }

    public static Point getDeviceSize() {
        WindowManager wm = (WindowManager) sInstance.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }



    public static String getBuildVersion() {
        try {
            PackageInfo pInfo = sInstance.getPackageManager().getPackageInfo(sInstance.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
