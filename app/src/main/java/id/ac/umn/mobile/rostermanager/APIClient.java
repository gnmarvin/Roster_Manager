package id.ac.umn.mobile.rostermanager;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Marvin on 6/5/2018.
 */

public class APIClient {
        public static Retrofit retrofit = null;

        //create connection
        public static Retrofit getApiClient(){
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(loggingInterceptor);

            if(retrofit == null){
                retrofit = new Retrofit.Builder()
                        .baseUrl("http://www.ccmychurch.net")    //BASE URL TARO SINI
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClientBuilder.build())
                        .build();
            }
            return retrofit;
        }
    }
