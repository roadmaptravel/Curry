package com.getroadmap.lib.request;

import android.content.Context;

import com.getroadmap.lib.models.Rates;
import com.getroadmap.lib.parsers.RatesParser;
import com.getroadmap.lib.utils.ConnectionUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurryApiClient {

    public static final String API_URL = "https://api.exchangeratesapi.io/";

    private CurryService curryService;
    private Context context;

    public CurryApiClient(Context context) {
        this.context = context;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(getGsonConverterFactory())
                .client(getClient())
                .build();

        // Create an instance of our API interface.
        curryService = retrofit.create(CurryService.class);
    }


    public CurryService getService() {
        return curryService;
    }

    private OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .cache(new Cache(context.getCacheDir(), 10 * 1024 * 1024)) // 10 MB
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Response originalResponse = chain.proceed(original);

                        if (ConnectionUtils.isNetworkConnected(context)) {
                            int maxAge = 24 * 60 * 60; // read from cache for 24 hours
                            return originalResponse.newBuilder()
                                    .header("Cache-Control", "public, max-age=" + maxAge)
                                    .build();
                        } else {
                            int maxStale = 30 * 24 * 60 * 60; // tolerate 30 days stale
                            return originalResponse.newBuilder()
                                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                    .build();
                        }
                    }
                })
                .build();

        return client;
    }


    /**
     * get gson converter factory
     * register type adapters here
     *
     * @return GsonConverterFactory
     */
    private GsonConverterFactory getGsonConverterFactory() {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Rates.class, new RatesParser())
                .create();

        GsonConverterFactory factory = GsonConverterFactory.create(gson);

        return factory;
    }


}