package com.cinatic.demo2.activities.splash;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.cinatic.demo2.activities.introduction.IntroductionActivity;
import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.activities.login.LoginActivity;
import com.cinatic.demo2.persistances.SettingPreferences;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SettingPreferences preferences = new SettingPreferences();
                if(preferences.isFirstRun()){
                    preferences.putFirstRun(false);
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, IntroductionActivity.class));
                } else {
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, 1000);
    }

}
