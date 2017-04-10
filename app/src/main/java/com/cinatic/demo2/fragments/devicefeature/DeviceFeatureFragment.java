package com.cinatic.demo2.fragments.devicefeature;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.badoo.mobile.util.WeakHandler;
import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.base.fragment.ButterKnifeFragment;
import com.cinatic.demo2.fragments.homedevice.DevicesPresenter;
import com.cinatic.demo2.fragments.homedevice.DevicesView;
import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.models.DeviceListItem;
import com.cinatic.demo2.views.adapters.DeviceListAdapter;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;

public class DeviceFeatureFragment extends ButterKnifeFragment{

    @BindView(R.id.linearlayout_feature_container_device_feature)
    LinearLayout mFeatureLinearLayout;

    FloatingActionMenu mMainFeatureMenu;
    private DeviceFeaturePresenter mPresenter;

    public static DeviceFeatureFragment newInstance() {
        DeviceFeatureFragment fragment = new DeviceFeatureFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new DeviceFeaturePresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device_feature, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMenu();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mMainFeatureMenu == null) return;
        mMainFeatureMenu.close(false);
        mMainFeatureMenu = null;
    }

    @OnTouch(R.id.linearlayout_feature_container_device_feature)
    boolean onMenuTouch(View view, MotionEvent motionEvent){
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                mFeatureLinearLayout.setBackground(AppApplication.getDrawableResource(R.drawable.menu_circle_hold_radius));
                break;
            case MotionEvent.ACTION_UP:
                mFeatureLinearLayout.setBackground(AppApplication.getDrawableResource(R.drawable.menu_circle_accent_radius));
                break;
        }
        return true;
    }

    @OnClick(R.id.imageview_minimize_device_feature)
    void onMinimizeClick(){
        mPresenter.showPreview();
    }

    public void initMenu() {
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(getActivity());

        ImageView mMusicImageView = new ImageView(getActivity());
        mMusicImageView.setImageDrawable(AppApplication.getDrawableResource(R.drawable.ic_sound));
        SubActionButton mMusicSAB = itemBuilder.setContentView(mMusicImageView).build();
        mMusicSAB.setBackground(AppApplication.getDrawableResource(R.drawable.menu_circle_accent_radius));

        ImageView mTempImageView = new ImageView(getActivity());
        mTempImageView.setImageDrawable(AppApplication.getDrawableResource(R.drawable.ic_temp));
        SubActionButton mTempSAB = itemBuilder.setContentView(mTempImageView).build();
        mTempSAB.setBackground(AppApplication.getDrawableResource(R.drawable.menu_circle_accent_radius));

        ImageView mRecordImageView = new ImageView(getActivity());
        mRecordImageView.setImageDrawable(AppApplication.getDrawableResource(R.drawable.ic_record));
        SubActionButton mRecordSAB = itemBuilder.setContentView(mRecordImageView).build();
        mRecordSAB.setBackground(AppApplication.getDrawableResource(R.drawable.menu_circle_accent_radius));

        ImageView mSnapshotImageView = new ImageView(getActivity());
        mSnapshotImageView.setImageDrawable(AppApplication.getDrawableResource(R.drawable.ic_snapshot));
        SubActionButton mSnapshotSAB = itemBuilder.setContentView(mSnapshotImageView).build();
        mSnapshotSAB.setBackground(AppApplication.getDrawableResource(R.drawable.menu_circle_accent_radius));

        ImageView mMuteImageView = new ImageView(getActivity());
        mMuteImageView.setImageDrawable(AppApplication.getDrawableResource(R.drawable.ic_mute));
        SubActionButton mMuteSAB = itemBuilder.setContentView(mMuteImageView).build();
        mMuteSAB.setBackground(AppApplication.getDrawableResource(R.drawable.menu_circle_accent_radius));

        mMainFeatureMenu = new FloatingActionMenu.Builder(getActivity())
                .addSubActionView(mMusicSAB)
                .addSubActionView(mTempSAB)
                .addSubActionView(mRecordSAB)
                .addSubActionView(mSnapshotSAB)
                .addSubActionView(mMuteSAB)
                .setStartAngle(-100)
                .setEndAngle(10)
                .attachTo(mFeatureLinearLayout)
                .build();

        new WeakHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mMainFeatureMenu == null) return;
                mMainFeatureMenu.open(true);
            }
        }, 200);
    }
}
