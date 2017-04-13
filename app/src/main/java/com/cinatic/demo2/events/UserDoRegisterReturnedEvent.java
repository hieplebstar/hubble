package com.cinatic.demo2.events;

import com.cinatic.demo2.models.responses.RegisterResponse;

import lombok.Data;

/**
 * Created by HiepLe on 21/2/2017.
 */

@Data
public class UserDoRegisterReturnedEvent {
    private final RegisterResponse registerResponse;
}
