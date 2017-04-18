package com.cinatic.demo2.views.adapters;

import android.app.usage.UsageEvents;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.models.responses.Device;
import com.cinatic.demo2.models.responses.DeviceEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Setter;


public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ItemViewHolder> {

    public interface OnClickItemListener{
        void onClickEvent(DeviceEvent item);
    }

    public interface OnScrollListener {
        void onReachBottom();
    }

    private List<DeviceEvent> mItems;

    @Setter
    private OnClickItemListener listener;
    @Setter
    private OnScrollListener mScrollListener;

    public EventListAdapter() {
        mItems = new ArrayList<>();
    }

    public void setItems(List<DeviceEvent> list) {
        if (list == null) return;
        this.mItems = list;
        notifyDataSetChanged();
    }

    public void addItems(List<DeviceEvent> list) {
        if (list == null) return;
        this.mItems.addAll(list);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_event_item)
        ImageView mImageView;
        @BindView(R.id.textview_event_item_time)
        TextView mTimeTextView;
        @BindView(R.id.textview_event_item_name)
        TextView mNameTextView;
        @BindView(R.id.textview_event_item_type)
        TextView mTypeTextView;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final DeviceEvent item = mItems.get(position);
        holder.mTimeTextView.setText(item.getCreatedDate());
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener == null) return;
                listener.onClickEvent(item);
            }
        });
        if(position == mItems.size()){
            if (mScrollListener == null) return;
            mScrollListener.onReachBottom();
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


}
