package com.cinatic.demo2.fragments.setup.activated;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinatic.demo2.base.fragment.ButterKnifeFragment;
import com.cinatic.demo2.hubble.R;

import butterknife.OnClick;

public class DeviceActivatedFragment extends ButterKnifeFragment {

    private DeviceActivatedPresenter mPresenter;

    public static DeviceActivatedFragment newInstance() {
        DeviceActivatedFragment fragment = new DeviceActivatedFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new DeviceActivatedPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device_activated, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.progressbutton_device_activated_ok)
    public void onButtonClick() {
        mPresenter.directToHome();
    }
}
