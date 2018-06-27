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
    @POST("/ccit_backend/auth/auth_rest/login")
    @FormUrlEncoded
    Call<JsonElement> Login(@Field("username") String username,
                                @Field("password") String password);

    /*
    REST UNTUK MENGAMBIL DATA LIST EVENT dan LIST TEAM / JOB  yang bertugas di event tersebut
     */
    @GET("/ccit_backend/event_roster/event_roster_rest/list")
    Call<JsonElement> Event();
    @GET("/ccit_backend/event_roster/event_roster_rest/list_job")
    Call<JsonElement>  Job(@Field("event_roster_id") String event_roster_id);

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
