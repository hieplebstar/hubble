package com.cinatic.demo2.dialogs.forgotpass;

import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.events.UserDoResetPasswordEvent;

/**
 * Created by Hiep.Le on 7/8/2016.
 */
public class ForgotPassDialogPresenter extends EventListeningPresenter<ForgotPassDialogView> {
    public void resetPassword(String userName, String email){
        post(new UserDoResetPasswordEvent(userName, email));
    }
}
