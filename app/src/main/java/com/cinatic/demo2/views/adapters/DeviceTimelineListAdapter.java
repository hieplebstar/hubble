package com.cinatic.demo2.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.models.DeviceTimelineListItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Setter;


public class DeviceTimelineListAdapter extends RecyclerView.Adapter<DeviceTimelineListAdapter.ItemViewHolder> {

    public interface OnClickItemListener {
        void onClickTimeline(DeviceTimelineListItem item);
    }

    private List<DeviceTimelineListItem> mItems;

    @Setter
    private OnClickItemListener listener;

    public DeviceTimelineListAdapter() {
        mItems = new ArrayList<>();
    }

    public void setItems(List<DeviceTimelineListItem> list) {
        if (list == null) return;
        this.mItems = list;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_device_timeline_item)
        ImageView mImageView;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_timeline, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final DeviceTimelineListItem item = mItems.get(position);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) return;
                listener.onClickTimeline(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


}
