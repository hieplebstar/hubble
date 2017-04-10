package com.cinatic.demo2.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.models.DeviceReplayListItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Setter;


public class DeviceReplayListAdapter extends RecyclerView.Adapter<DeviceReplayListAdapter.ItemViewHolder> {

    public interface OnClickItemListener {
        void onClickReplay(DeviceReplayListItem item);
    }

    private List<DeviceReplayListItem> mItems;

    @Setter
    private OnClickItemListener listener;

    public DeviceReplayListAdapter() {
        mItems = new ArrayList<>();
    }

    public void setItems(List<DeviceReplayListItem> list) {
        if (list == null) return;
        this.mItems = list;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_device_replay_item)
        ImageView mImageView;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_replay, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final DeviceReplayListItem item = mItems.get(position);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) return;
                listener.onClickReplay(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


}
