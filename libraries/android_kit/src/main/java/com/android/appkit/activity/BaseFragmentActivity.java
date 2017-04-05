package com.android.appkit.activity;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.android.appkit.AndroidApplication;
import com.android.appkit.event.FragmentDoAttachEvent;
import com.android.appkit.event.FragmentDoClearBackStackEvent;
import com.android.appkit.event.FragmentDoDetachEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by HiepLe on 4/2/2017.
 */

public abstract class BaseFragmentActivity  extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{

    protected abstract int getContainerId(Class<? extends Fragment> fragmentClass);
    protected String mHomeTitle;
    protected Fragment mHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplication.getEvenBusController().registerSubscriber(this);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AndroidApplication.getEvenBusController().unregisterSubscriber(this);
    }

    @Override
    public void onBackStackChanged(){
        if(isBackStackEmpty()){
            updateActionBar(mHomeFragment, mHomeTitle);
            return;
        }
        FragmentManager.BackStackEntry entry = getBackStackCurrentItem();
        if(entry == null) return;
        updateActionBar(getFragment(entry.getName()), entry.getBreadCrumbTitle().toString());
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAttachFragmentEvent(FragmentDoAttachEvent event) {
        Fragment fragment = event.fragment;
        if (fragment instanceof DialogFragment) {
            openDialog((DialogFragment) fragment);
        } else {
            attachFragment(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDetachFragmentEvent(FragmentDoDetachEvent event) {
        String tag = event.fragmentClass.getName();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment == null) return;
        removeFragment(fragment, tag);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClearBackStackEvent(FragmentDoClearBackStackEvent event) {
        clearBackStackFragments();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Object event) {
        Log.d("Eventbus",event.getClass().getName());
    }

    private void openDialog(DialogFragment dialog) {
        dialog.show(getSupportFragmentManager(), ((Object) dialog).getClass().getSimpleName());
    }

    private void attachFragment(FragmentDoAttachEvent event) {
        int containerId = getContainerId(event.fragment.getClass());
        if(getSupportFragmentManager().findFragmentByTag(event.fragment.getClass().getName()) != null){
            getSupportFragmentManager().popBackStackImmediate(event.fragment.getClass().getName(), 0);
        }
        if (!event.addToBackStack) {
            clearBackStackFragments();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (event.addToBackStack) {
            transaction.addToBackStack(event.fragment.getClass().getName());
            transaction.setBreadCrumbTitle(event.actionBarTitle);
        } else {
            mHomeTitle = event.actionBarTitle;
            mHomeFragment = event.fragment;
            updateActionBar(mHomeFragment, mHomeTitle);
        }
        transaction.setCustomAnimations(event.enterAnimation, event.exitAnimation, event.popEnterAnimation, event.popExitAnimation);
        transaction.replace(containerId, event.fragment, event.fragment.getClass().getName());
        transaction.commit();
    }

    private void bringFragmentToFront(FragmentDoAttachEvent event) {
        getSupportFragmentManager().popBackStackImmediate(event.fragment.getClass().getName(), 0);
        attachFragment(event);
    }

    private void clearBackStackFragments() {
        FragmentManager fm = getSupportFragmentManager();
        while (fm.getBackStackEntryCount() != 0) {
            FragmentManager.BackStackEntry entry = fm.getBackStackEntryAt(0);
            Fragment fragment = fm.findFragmentByTag(entry.getName());
            if (fragment != null) {
                removeFragment(fragment, fragment.getTag());
            }
        }
    }

    private void removeFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment);
        transaction.commitAllowingStateLoss();
        getSupportFragmentManager().popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private Fragment getFragment(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    protected void updateActionBar(Fragment fragment, String title) {
        getSupportActionBar().setTitle(title);
        getSupportActionBar().invalidateOptionsMenu();
    }

    protected boolean isBackStackEmpty() {
        FragmentManager fm = getSupportFragmentManager();
        return fm.getBackStackEntryCount() == 0;
    }

    public FragmentManager.BackStackEntry getBackStackCurrentItem() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 0) {
            return null;
        }
        FragmentManager.BackStackEntry currentEntry = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
        return currentEntry;
    }
}
