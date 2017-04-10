package com.cinatic.demo2.fragments.homedevice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinatic.demo2.base.fragment.ButterKnifeFragment;
import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.models.DeviceListItem;
import com.cinatic.demo2.views.adapters.DeviceListAdapter;

import java.util.List;

import butterknife.BindView;

public class DevicesFragment extends ButterKnifeFragment implements DevicesView, DeviceListAdapter.OnClickItemListener{

    @BindView(R.id.recyclerview_home_devices)
    RecyclerView mRecyclerView;

    private DevicesPresenter mPresenter;
    private DeviceListAdapter mAdapter;

    public static DevicesFragment newInstance() {
        DevicesFragment fragment = new DevicesFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new DevicesPresenter();
        mAdapter = new DeviceListAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_devices, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setListener(this);
        mPresenter.start(this);
        loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.stop();
    }

    @Override
    public void showDeviceList(List<DeviceListItem> deviceList) {
        if (deviceList == null) return;
        mAdapter.setItems(deviceList);
    }

    private void loadData() {
        mPresenter.loadDeviceList();
    }

    @Override
    public void onClickDevice(DeviceListItem item) {
        mPresenter.showDetail();
    }
}