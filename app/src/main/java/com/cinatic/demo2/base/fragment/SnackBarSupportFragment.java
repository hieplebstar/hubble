package com.cinatic.demo2.base.fragment;

import android.support.v4.app.Fragment;

import com.cinatic.demo2.activities.main.MainActivity;

public class SnackBarSupportFragment extends Fragment {

    public void showSnackBar(String message){
        if(getActivity() == null) return;
        if(getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showSnackBar(message);
        }
    }
}
