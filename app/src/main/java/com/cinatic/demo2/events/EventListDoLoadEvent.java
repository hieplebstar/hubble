package com.cinatic.demo2.events;

import lombok.Data;

/**
 * Created by HiepLe on 4/4/2017.
 */
@Data
public class EventListDoLoadEvent {
    final String deviceIds;
    final int page;
}
