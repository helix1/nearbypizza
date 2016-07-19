package io.agileninja.nearbypizza.network;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YQLClient {

    private static final String BASE_URL = "http://query.yahooapis.com/v1/public/";
    private final Retrofit retrofit;

    public YQLClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <T> T create(Class<T> apiInterfaceClass) {
        return retrofit.create(apiInterfaceClass);
    }
}
