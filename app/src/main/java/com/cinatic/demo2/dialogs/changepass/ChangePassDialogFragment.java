package com.cinatic.demo2.dialogs.changepass;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.dialogs.forgotpass.ForgotPassDialogPresenter;
import com.cinatic.demo2.dialogs.forgotpass.ForgotPassDialogView;
import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Hiep.Le on 7/7/2016.
 */
public class ChangePassDialogFragment extends DialogFragment implements ForgotPassDialogView {


    @BindView(R.id.edittext_change_pass_dialog_old_pass)
    EditText mOldEditText;
    @BindView(R.id.edittext_change_pass_dialog_new_pass)
    EditText mNewEditText;
    @BindView(R.id.edittext_change_pass_dialog_confirm_pass)
    EditText mConfirmEditText;

    private Unbinder mUnbinder;
    private ChangePassDialogPresenter mPresenter;

    public static ChangePassDialogFragment newInstance() {
        ChangePassDialogFragment dialogFragment = new ChangePassDialogFragment();
        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ChangePassDialogPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View content = getActivity().getLayoutInflater().inflate(R.layout.dialog_change_pass, null);
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
                            mPresenter.changePassword(mOldEditText.getText().toString(), mNewEditText.getText().toString(), mConfirmEditText.getText().toString());
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
        String oldPass = mOldEditText.getText().toString();
        String newPass = mNewEditText.getText().toString();
        String confirmPass = mConfirmEditText.getText().toString();
        if (TextUtils.isEmpty(oldPass)){
            Toast.makeText(getActivity(), AppApplication.getStringResource(R.string.warning_password), Toast.LENGTH_LONG).show();
            return false;
        }
        if (TextUtils.isEmpty(newPass)) {
            Toast.makeText(getActivity(), AppApplication.getStringResource(R.string.warning_password), Toast.LENGTH_LONG).show();
            return false;
        }
        if (!confirmPass.equals(newPass)) {
            Toast.makeText(getActivity(), AppApplication.getStringResource(R.string.warning_confirm_password), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}
