package com.cinatic.demo2.activities.introduction;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import com.cinatic.demo2.activities.login.LoginActivity;
import com.cinatic.demo2.base.activity.CalligraphyFontActivity;
import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.activities.main.MainActivity;
import com.cinatic.demo2.views.adapters.SliderAdapter;
import me.relex.circleindicator.CircleIndicator;

public class IntroductionActivity extends CalligraphyFontActivity {

    @BindView(R.id.viewpager_introduction)
    ViewPager mPager;
    @BindView(R.id.indicator_introduction)
    CircleIndicator mCircleIndicator;

    private Unbinder mUnbinder;
    private SliderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        mUnbinder = ButterKnife.bind(this);
        mAdapter = new SliderAdapter(this);
        mPager.setAdapter(mAdapter);
        mCircleIndicator.setViewPager(mPager);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @OnClick(R.id.progressbutton_introduction_next)
    public void onLoginClick() {
        directToLoginActivity();
    }

    public void directToLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
