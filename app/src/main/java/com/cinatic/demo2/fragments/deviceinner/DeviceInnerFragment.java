package com.cinatic.demo2.fragments.deviceinner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinatic.demo2.base.fragment.ButterKnifeFragment;
import com.cinatic.demo2.fragments.homedevice.DevicesPresenter;
import com.cinatic.demo2.fragments.homedevice.DevicesView;
import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.models.DeviceListItem;
import com.cinatic.demo2.models.DeviceReplayListItem;
import com.cinatic.demo2.models.DeviceTimelineListItem;
import com.cinatic.demo2.views.adapters.DeviceListAdapter;
import com.cinatic.demo2.views.adapters.DeviceReplayListAdapter;
import com.cinatic.demo2.views.adapters.DeviceTimelineListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DeviceInnerFragment extends ButterKnifeFragment implements DeviceInnerView{

    @BindView(R.id.recyclerview_replay_device_inner)
    RecyclerView mReplayRecyclerView;
    @BindView(R.id.recyclerview_timeline_device_inner)
    RecyclerView mTimelineRecyclerView;

    private DeviceInnerPresenter mPresenter;
    private DeviceReplayListAdapter mReplayAdapter;
    private DeviceTimelineListAdapter mTimelineAdapter;

    public static DeviceInnerFragment newInstance() {
        DeviceInnerFragment fragment = new DeviceInnerFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new DeviceInnerPresenter();
        mReplayAdapter = new DeviceReplayListAdapter();
        mTimelineAdapter = new DeviceTimelineListAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device_inner, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager horizontalLinearLayoutManager = new LinearLayoutManager(getActivity());
        horizontalLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mReplayRecyclerView.setLayoutManager(horizontalLinearLayoutManager);
        mReplayRecyclerView.setAdapter(mReplayAdapter);

        LinearLayoutManager verticalLinearLayoutManager = new LinearLayoutManager(getActivity());
        verticalLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mTimelineRecyclerView.setLayoutManager(verticalLinearLayoutManager);
        mTimelineRecyclerView.setAdapter(mTimelineAdapter);

        mPresenter.start(this);
        loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.stop();
    }

    @Override
    public void showReplayList(List<DeviceReplayListItem> replayList) {
        if (replayList == null) return;
        mReplayAdapter.setItems(replayList);
    }

    @Override
    public void showTimelineList(List<DeviceTimelineListItem> timelineList) {
        if (timelineList == null) return;
        mTimelineAdapter.setItems(timelineList);
    }

    @OnClick(R.id.imageview_device_inner)
    void onPreviewClick(){
        mPresenter.showFullScreen();
    }

    private void loadData() {
        mPresenter.loadReplayList();
        mPresenter.loadTimelineList();
    }
}
