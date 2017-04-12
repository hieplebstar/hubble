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
        @Getter
        final String os = "1";

        APIEnvironment(String domain) {
            this.domain = domain;
        }

        public static APIEnvironment getByOrdinal(int ordinal) {
            return APIEnvironment.values()[ordinal];
        }
    }

//    private static APIEnvironment sAPIEnvironment = null;

//    public static void setupAPIEnvironment(APIEnvironment environment) {
//        sAPIEnvironment = environment;
//        BaseManager.initRetrofit();
//    }

    public static APIEnvironment getAPIEnvironment() {
//        if (null != sAPIEnvironment) {
//            return sAPIEnvironment;
//        }
//        switch (BuildConfig.BUILD_TYPE) {
//            case "debug":
                return APIEnvironment.STAGING;
//            default:
//                return APIEnvironment.PRODUCTION;
//        }
    }
}
