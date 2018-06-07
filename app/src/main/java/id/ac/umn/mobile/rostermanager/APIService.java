package id.ac.umn.mobile.rostermanager;


import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by Marvin on 6/5/2018.
 */

public interface APIService {
    @PUT("/auth/auth_login_rest")
    @FormUrlEncoded
    Call<JsonElement> Login(@Field("username") String username,
                                @Field("password") String password);
    @GET("auth/auth_login_rest")
    Call<JsonElement> Loginpakeget();

//    @POST("signup.php")
//    @FormUrlEncoded
//    Call<JsonElement> SignUpQuiz(@Field("username") String username,
//                                 @Field("password") String password,
//                                 @Field("name") String name);
//
//    @POST("chat.php")
//    @FormUrlEncoded
//    Call<JsonElement> SendMessage(@Field("name") String name,
//                                  @Field("message") String message);
//
//    @GET("chat.php")
//    Call<JsonElement> GetAllMessage();
}
