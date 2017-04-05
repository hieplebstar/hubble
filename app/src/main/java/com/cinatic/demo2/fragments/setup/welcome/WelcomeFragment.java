package com.cinatic.demo2.fragments.setup.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinatic.demo2.base.fragment.ButterKnifeFragment;
import com.cinatic.demo2.hubble.R;

import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class WelcomeFragment extends ButterKnifeFragment {

    private final int WIFI_REQUEST_CODE = 0;

    private WelcomePresenter mPresenter;

    public static WelcomeFragment newInstance() {
        WelcomeFragment fragment = new WelcomeFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new WelcomePresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != WIFI_REQUEST_CODE) return;
        mPresenter.showSetupResult();

    }

    @OnClick(R.id.progressbutton_welcome_add)
    public void onAddButtonClick() {
        startActivityForResult(new Intent(Settings.ACTION_WIFI_SETTINGS), WIFI_REQUEST_CODE);
    }
}
