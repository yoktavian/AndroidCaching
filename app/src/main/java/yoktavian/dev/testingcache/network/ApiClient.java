package yoktavian.dev.testingcache.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yoktavian on 11/17/17.
 */

public class ApiClient {
    private static final String URL = "http://pratikbutani.x10.mx";

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getApiService(){
        return getRetrofitInstance().create(ApiService.class);
    }
}
