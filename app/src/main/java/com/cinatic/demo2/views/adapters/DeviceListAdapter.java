package com.cinatic.demo2.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.models.DeviceListItem;
import com.cinatic.demo2.models.responses.Device;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Setter;


public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.ItemViewHolder> {

    public interface OnClickItemListener{
        void onClickDevice(Device item);
    }

    private List<Device> mItems;

    @Setter
    private OnClickItemListener listener;

    public DeviceListAdapter() {
        mItems = new ArrayList<>();
    }

    public void setItems(List<Device> list) {
        if (list == null) return;
        this.mItems = list;
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_device_item)
        ImageView mImageView;
        @BindView(R.id.textview_device_item)
        TextView mTextView;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final Device item = mItems.get(position);
        holder.mTextView.setText(item.getName());
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener == null) return;
                listener.onClickDevice(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


}
