package com.cinatic.demo2.dialogs.forgotpass;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.utils.StringUtils;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by shintabmt on 7/7/2016.
 */
public class ForgotPassDialogFragment extends DialogFragment implements ForgotPassDialogView {


    @BindView(R.id.edittext_fogot_pass_dialog_email)
    EditText mEmailEditText;
    @BindView(R.id.edittext_fogot_pass_dialog_username)
    EditText mUserNameEditText;

    private Unbinder mUnbinder;
    private ForgotPassDialogPresenter mPresenter;

    public static ForgotPassDialogFragment newInstance() {
        ForgotPassDialogFragment dialogFragment = new ForgotPassDialogFragment();
        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ForgotPassDialogPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View content = getActivity().getLayoutInflater().inflate(R.layout.dialog_fogot_pass, null);
        mUnbinder = ButterKnife.bind(this, content);
        final AlertDialog mAlertDialog;
        mAlertDialog= new AlertDialog.Builder(getActivity())
                .setTitle(AppApplication.getStringResource(R.string.reset_pass_label))
                .setView(content)
                .setPositiveButton(AppApplication.getStringResource(R.string.ok_label), null)
                .setNegativeButton(AppApplication.getStringResource(R.string.cancel_label), null).create();
        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if(validate()){
                            mPresenter.resetPassword(mUserNameEditText.getText().toString(), mEmailEditText.getText().toString());
                            dismiss();
                        }
                    }
                });
            }
        });
        return mAlertDialog;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    private boolean validate(){
        String userName = mUserNameEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        if (TextUtils.isEmpty(userName)){
            Toast.makeText(getActivity(), AppApplication.getStringResource(R.string.warning_username), Toast.LENGTH_LONG).show();
            return false;
        }
        if (!StringUtils.validateEmail(email)) {
            Toast.makeText(getActivity(), AppApplication.getStringResource(R.string.warning_email), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}
