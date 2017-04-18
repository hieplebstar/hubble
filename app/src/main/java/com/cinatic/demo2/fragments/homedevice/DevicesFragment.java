package com.cinatic.demo2.fragments.homedevice;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cinatic.demo2.base.fragment.ButterKnifeFragment;
import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.models.DeviceListItem;
import com.cinatic.demo2.models.responses.Device;
import com.cinatic.demo2.views.adapters.DeviceListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DevicesFragment extends ButterKnifeFragment implements DevicesView, DeviceListAdapter.OnClickItemListener{

    private final int WIFI_REQUEST_CODE = 0;

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
        setHasOptionsMenu(true);
        mPresenter = new DevicesPresenter();
        mAdapter = new DeviceListAdapter();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.device_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_device_menu_item:
                startActivityForResult(new Intent(Settings.ACTION_WIFI_SETTINGS), WIFI_REQUEST_CODE);
                return true;
        }
        return false;
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != WIFI_REQUEST_CODE) return;
        mPresenter.showSetupResult();

    }

    @Override
    public void showDeviceList(List<Device> deviceList) {
        if (deviceList == null) return;
        mAdapter.setItems(deviceList);
    }

    private void loadData() {
        mPresenter.loadDeviceList();
    }

    @Override
    public void onClickDevice(Device item) {
        mPresenter.showDetail(item.getId(), item.getName(), item.getDeviceId());
    }
}
