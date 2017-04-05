package com.cinatic.demo2.views.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.hubble.R;

public class SliderAdapter extends PagerAdapter {

    private static final int NUM_PAGE = 3;

    Context mContext;
    LayoutInflater mLayoutInflater;

    public SliderAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return NUM_PAGE;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View contentView = mLayoutInflater.inflate(R.layout.item_image_slider, view, false);

        if (contentView == null) return null;
        ViewHolder viewHolder = new ViewHolder(contentView);
        switch (position){
            case 0: viewHolder.mImageView.setImageDrawable(AppApplication.getDrawableResource(R.drawable.introduction_1));
                break;

            case 1: viewHolder.mImageView.setImageDrawable(AppApplication.getDrawableResource(R.drawable.introduction_2));
                break;

            case 2: viewHolder.mImageView.setImageDrawable(AppApplication.getDrawableResource(R.drawable.introduction_3));
                break;
        }
        view.addView(contentView);
        return contentView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    static class ViewHolder {
        @BindView(R.id.imageview_slider)
        ImageView mImageView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
