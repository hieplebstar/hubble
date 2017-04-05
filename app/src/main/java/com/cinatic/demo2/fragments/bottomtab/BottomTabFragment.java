package com.cinatic.demo2.fragments.bottomtab;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.base.fragment.ButterKnifeFragment;
import com.cinatic.demo2.hubble.R;

import butterknife.BindView;

/**
 * Created by HiepLe on 8/11/2016.
 */
public class BottomTabFragment extends ButterKnifeFragment implements TabLayout.OnTabSelectedListener, BottomTabView {

    @BindView(R.id.tab_layout_bottom)
    TabLayout mTabLayout;

    private BottomTabPresenter mPresenter;

    public static BottomTabFragment newInstance() {
        return new BottomTabFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new BottomTabPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupTabBar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        filterIconColor(tab.getIcon(), R.color.colorAccent);
        mPresenter.bottomTabSelected(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        filterIconColor(tab.getIcon(), R.color.gray);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        filterIconColor(tab.getIcon(), R.color.colorAccent);
        mPresenter.bottomTabSelected(tab.getPosition());
    }

    @Override
    public void updateTabSelectedItem(final int tabIndex) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TabLayout.Tab currentTab = mTabLayout.getTabAt(tabIndex);
                if (currentTab != null) {
                    View customView = currentTab.getCustomView();
                    if (customView != null) {
                        customView.setSelected(true);
                    }
                    currentTab.select();
                }
            }
        }, 100);
    }

    private void setupTabBar() {
        mTabLayout.addTab(mTabLayout.newTab()
                .setIcon(R.drawable.ic_camera));
        mTabLayout.addTab(mTabLayout.newTab()
                .setIcon(R.drawable.ic_video));
        mTabLayout.addTab(mTabLayout.newTab()
                .setIcon(R.drawable.ic_notification));
        mTabLayout.addTab(mTabLayout.newTab()
                .setIcon(R.drawable.ic_timetable));
        mTabLayout.addOnTabSelectedListener(this);
        updateTabSelectedItem(0);
    }

    private Drawable filterIconColor(Drawable drawable, @ColorRes int resColorId) {
        drawable.setColorFilter(AppApplication.getIntColor(resColorId), PorterDuff.Mode.SRC_IN);
        return drawable;
    }
}
