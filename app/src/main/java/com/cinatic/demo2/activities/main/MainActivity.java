package com.cinatic.demo2.activities.main;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.appkit.ActionBarMode;
import com.android.appkit.activity.BaseFragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.cinatic.demo2.fragments.bottomtab.BottomTabFragment;
import com.cinatic.demo2.hubble.R;

public class MainActivity extends BaseFragmentActivity implements MainView{

    @BindView(R.id.toolbar_main)
    Toolbar mToolbar;
    @BindView(R.id.container_main)
    View mMainContainer;
    @BindView(R.id.framelayout_tab_main)
    View mBottomTabContainer;

    private Unbinder mUnbinder;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mPresenter = new MainPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    protected int getContainerId(Class<? extends Fragment> fragmentClass) {
        if(fragmentClass.equals(BottomTabFragment.class)){
            return R.id.framelayout_tab_main;
        }
        return R.id.framelayout_main_container;
    }

    @Override
    protected void updateActionBar(Fragment fragment, String title) {
        super.updateActionBar(fragment, title);
        if(fragment == null) return;
        switch ((ActionBarMode)fragment.getArguments().getSerializable(ActionBarMode.BUNDLE_ID)){
            case HOME_MODE:
                mToolbar.setVisibility(View.VISIBLE);
                mBottomTabContainer.setVisibility(View.VISIBLE);
                break;
            case FULL_MODE:
                mToolbar.setVisibility(View.GONE);
                mBottomTabContainer.setVisibility(View.GONE);
                break;
        }
    }
}
