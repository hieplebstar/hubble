package com.cinatic.demo2.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean validateEmail(String email) {
        return !TextUtils.isEmpty(email) && email.contains("@") && email.contains(".") && email.indexOf("@") != 0 && email.indexOf(".") != 0;
    }

    public static String getUserNameFromEmail(String email) {
        int position  = email.indexOf("@");
        if(position <= 0 || position >= email.length()) return "";
        return email.substring(0, position);
    }

}
