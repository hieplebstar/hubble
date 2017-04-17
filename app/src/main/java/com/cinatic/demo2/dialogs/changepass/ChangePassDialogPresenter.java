package com.cinatic.demo2.dialogs.changepass;

import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.dialogs.forgotpass.ForgotPassDialogView;
import com.cinatic.demo2.events.UserDoChangePasswordEvent;
import com.cinatic.demo2.events.UserDoResetPasswordEvent;

/**
 * Created by Hiep.Le on 7/8/2016.
 */
public class ChangePassDialogPresenter extends EventListeningPresenter<ForgotPassDialogView> {
    public void changePassword(String oldPass, String newPass, String confirmPass){
        post(new UserDoChangePasswordEvent(oldPass, newPass, confirmPass));
    }
}
