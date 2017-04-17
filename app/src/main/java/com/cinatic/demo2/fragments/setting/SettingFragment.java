package com.cinatic.demo2.fragments.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.base.fragment.ButterKnifeFragment;
import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.models.responses.UserInfo;

import butterknife.BindView;

public class SettingFragment extends ButterKnifeFragment implements SettingView{

    @BindView(R.id.textview_app_version_setting)
    TextView mVersionTextView;
    @BindView(R.id.textview_username_setting)
    TextView mUsernameTextView;
    @BindView(R.id.textview_email_setting)
    TextView mEmailTextView;

    private SettingPresenter mPresenter;

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SettingPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.start(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.stop();
    }

    @Override
    public void updateView(UserInfo userInfo) {
        mUsernameTextView.setText(userInfo.getUserName());
        mVersionTextView.setText(AppApplication.getStringResource(R.string.app_version_label, AppApplication.getBuildVersion()));
    }
}
