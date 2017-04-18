package com.cinatic.demo2.fragments.homeevent;

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
import com.cinatic.demo2.fragments.homedevice.DevicesPresenter;
import com.cinatic.demo2.fragments.homedevice.DevicesView;
import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.models.responses.Device;
import com.cinatic.demo2.models.responses.DeviceEvent;
import com.cinatic.demo2.views.adapters.DeviceListAdapter;
import com.cinatic.demo2.views.adapters.EventListAdapter;

import java.util.List;

import butterknife.BindView;

public class EventFragment extends ButterKnifeFragment implements EventView, EventListAdapter.OnClickItemListener{

    private final int WIFI_REQUEST_CODE = 0;

    @BindView(R.id.recyclerview_home_event)
    RecyclerView mRecyclerView;

    private EventPresenter mPresenter;
    private EventListAdapter mAdapter;

    public static EventFragment newInstance() {
        EventFragment fragment = new EventFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new EventPresenter();
        mAdapter = new EventListAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
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
    public void showEventList(List<DeviceEvent> eventList) {
        if (eventList == null) return;
        mAdapter.addItems(eventList);
    }

    private void loadData() {
        mPresenter.loadEventList();
    }

    @Override
    public void onClickEvent(DeviceEvent item) {
//        mPresenter.showDetail(item.getId(), item.getName(), item.getDeviceId());
    }
}
