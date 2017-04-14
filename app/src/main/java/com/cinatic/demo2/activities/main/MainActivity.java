package com.cinatic.demo2.activities.main;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.appkit.ActionBarMode;
import com.android.appkit.activity.BaseFragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import com.cinatic.demo2.base.activity.CalligraphyFontActivity;
import com.cinatic.demo2.base.activity.CalligraphyFontFragmentActivity;
import com.cinatic.demo2.fragments.bottomtab.BottomTabFragment;
import com.cinatic.demo2.hubble.R;

public class MainActivity extends CalligraphyFontFragmentActivity implements MainView{

    @BindView(R.id.toolbar_main)
    Toolbar mToolbar;
    @BindView(R.id.container_main)
    View mMainContainer;
    @BindView(R.id.framelayout_tab_main)
    View mBottomTabContainer;
    @BindView(R.id.textview_title_main)
    TextView mTitleTextView;
    @BindView(R.id.progressbar_main)
    ProgressBar mProgressBar;

    private Unbinder mUnbinder;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mPresenter = new MainPresenter();
        mPresenter.start(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mPresenter.stop();
    }

    @Override
    protected int getContainerId(Class<? extends Fragment> fragmentClass) {
        if(fragmentClass.equals(BottomTabFragment.class)){
            return R.id.framelayout_tab_main;
        }
        return R.id.framelayout_main_container;
    }

    @Override
    protected void updateActionBar(ActionBarMode actionBarMode, Fragment fragment, String title) {
        super.updateActionBar(actionBarMode, fragment, title);
        switch (actionBarMode){
            case HOME_MODE:
                mToolbar.setVisibility(View.VISIBLE);
                mBottomTabContainer.setVisibility(View.VISIBLE);
                break;
            case FULL_MODE:
                mToolbar.setVisibility(View.GONE);
                mBottomTabContainer.setVisibility(View.GONE);
                break;
            case OVERLAY_MODE:
                mToolbar.setVisibility(View.VISIBLE);
                mBottomTabContainer.setVisibility(View.VISIBLE);
                break;
        }
        if(TextUtils.isEmpty(title)) return;
        mTitleTextView.setText(title);
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(mMainContainer, message, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        textView.setMaxLines(5);
        snackbar.show();
    }

    @Override
    public void showLoading(boolean isLoading) {
        if(mProgressBar == null) return;
        if(isLoading) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.imageview_menu_setting_main)
    public void onSettingButtonClick() {
        mPresenter.showSetting();
    }
}
