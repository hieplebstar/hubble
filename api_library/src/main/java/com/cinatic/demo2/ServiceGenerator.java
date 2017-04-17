package com.cinatic.demo2;

import android.content.Context;

import com.cinatic.demo2.models.responses.AuthenticationToken;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.Setter;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HiepLe on 10/10/2016.
 */

public class ServiceGenerator {
    private static OkHttpClient.Builder httpClient;
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(AppEnvironmentManager.getAPIEnvironment().getDomain())
                    .addConverterFactory(GsonConverterFactory.create());
    private static Context mContext;

    @Setter @Getter
    private static String accessToken;
    @Setter @Getter
    final static int os = 1;

    public static void initialize(Context context){
        if (mContext == null){
            mContext = context;
        }
    }
    public static <S> S createService(Class<S> serviceClass) {
        return retrofit().create(serviceClass);
    }
    private static Retrofit retrofit() {
//        if (httpClient == null){
            configHttpClient();
//        }
        return builder.client(httpClient.build()).build();
    }

    private static void configHttpClient() {
        addLogging();
//        addDefaultHeader();
    }

    private static void addLogging() {
        httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(10000, TimeUnit.MILLISECONDS);
//        if (BuildConfig.BUILD_TYPE.equals("debug")) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);
//        }
    }
    private static void addDefaultHeader(){
//        httpClient.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//               Request.Builder builder = chain.request().newBuilder();
//
//                if (authenticationToken!= null){
//                    String bearerToken = authenticationToken.getTokenType() + " " + authenticationToken.getAccessToken();
//                    builder.addHeader("Authorization", bearerToken);
//                }
//                return chain.proceed(builder.build());
//            }
//        });
    }
}
