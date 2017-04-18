package com.cinatic.demo2;

import android.widget.ImageView;

import com.android.appkit.AndroidApplication;
import com.squareup.picasso.Picasso;

/**
 * Created by HiepLe on 4/4/2017.
 */

public class AppApplication extends AndroidApplication {
    @Override
    protected com.android.appkit.eventbus.EventBusController createEventBusController() {
        return new MainEventBusController();
    }

    public static void loadImage(String url, int placeHolderResId, ImageView imageView) {
        Picasso.with(AppApplication.getInstance())
                .load(url)
                .placeholder(placeHolderResId)
                .into(imageView);
    }
}
