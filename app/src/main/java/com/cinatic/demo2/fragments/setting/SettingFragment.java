package com.cinatic.demo2.fragments.setting;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.activities.login.LoginActivity;
import com.cinatic.demo2.base.fragment.ButterKnifeFragment;
import com.cinatic.demo2.dialogs.changepass.ChangePassDialogFragment;
import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.models.responses.UserInfo;

import butterknife.BindView;
import butterknife.OnClick;

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
        setHasOptionsMenu(true);
        mPresenter = new SettingPresenter();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.setting_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_setting_menu_item:
                doLogout();
                return true;
        }
        return false;
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

    @OnClick(R.id.textview_setting_change_pass)
    public void onChangePassClick() {
        ChangePassDialogFragment changePassDialog = ChangePassDialogFragment.newInstance();
        changePassDialog.show(getFragmentManager(), "");
    }

    public void doLogout() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }
}
