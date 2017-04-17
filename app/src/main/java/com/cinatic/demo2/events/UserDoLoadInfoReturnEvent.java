package com.cinatic.demo2.events;

import com.cinatic.demo2.models.responses.UserInfo;

import lombok.Data;

/**
 * Created by HiepLe on 4/4/2017.
 */
@Data
public class UserDoLoadInfoReturnEvent {
    final UserInfo userInfo;
}
