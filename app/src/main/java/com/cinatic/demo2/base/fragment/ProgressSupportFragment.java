package com.cinatic.demo2.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cinatic.demo2.activities.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProgressSupportFragment extends SnackBarSupportFragment {

    public void showLoading(boolean isLoading) {
        if(getActivity() == null) return;
        if(getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showLoading(isLoading);
        }
    }
}
