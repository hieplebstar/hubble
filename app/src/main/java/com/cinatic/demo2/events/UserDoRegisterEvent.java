package com.cinatic.demo2.events;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by HiepLe on 21/2/2017.
 */

@Data
@AllArgsConstructor
public class UserDoRegisterEvent {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}
