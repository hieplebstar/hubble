package com.cinatic.demo2.events;

import lombok.Data;

/**
 * Created by HiepLe on 4/4/2017.
 */
@Data
public class UserDoChangePasswordEvent {
    final String oldPass;
    final String newPass;
    final String confirmPass;
}
