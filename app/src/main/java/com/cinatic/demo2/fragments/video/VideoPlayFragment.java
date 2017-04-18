package com.cinatic.demo2.fragments.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.cinatic.demo2.base.fragment.ButterKnifeFragment;
import com.cinatic.demo2.hubble.R;

import butterknife.BindView;

public class VideoPlayFragment extends ButterKnifeFragment {

    private final static String EXTRA_VIDEO_URL = "extra_video_url";

    @BindView(R.id.webview_video_play)
    WebView mWebView;

    public static VideoPlayFragment newInstance(String url) {
        VideoPlayFragment fragment = new VideoPlayFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_VIDEO_URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_play, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWebView.loadUrl(getArguments().getString(EXTRA_VIDEO_URL));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
