package com.cinatic.demo2;


import lombok.Getter;

/**
 * Created by HiepLe on 10/10/2016.
 */

public class AppEnvironmentManager {
    public enum APIEnvironment {
        STAGING("https://dev-api.cinatic.com/v1/"),
        PRODUCTION("https://api.cinatic.com/v1/");



        @Getter
        final String domain;

        APIEnvironment(String domain) {
            this.domain = domain;
        }

        public static APIEnvironment getByOrdinal(int ordinal) {
            return APIEnvironment.values()[ordinal];
        }
    }


    public static APIEnvironment getAPIEnvironment() {
        switch (BuildConfig.BUILD_TYPE) {
            case "debug":
                return APIEnvironment.STAGING;
            default:
                return APIEnvironment.PRODUCTION;
        }
    }
}
