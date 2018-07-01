package id.ac.umn.mobile.rostermanager;


import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Marvin on 6/5/2018.
 */

public interface APIService {
    @POST("/ccit_backend/auth/auth_rest/login")
    @FormUrlEncoded
    Call<JsonElement> Login(@Field("username") String username,
                                @Field("password") String password);

    @POST("/ccit_backend/auth/auth_rest/recovery")
    @FormUrlEncoded
    Call<JsonElement> Recovery(@Field("email") String email);

    /*
    REST UNTUK MENGAMBIL DATA LIST EVENT dan LIST TEAM / JOB  yang bertugas di event tersebut
     */
    @GET("/ccit_backend/event_roster/event_roster_rest/list")
    Call<JsonElement> EventRoster(@Header("token_id") String token_id);
    @GET("/ccit_backend/event_roster/event_roster_rest/list_job")
    Call<JsonElement> JobRoster(@Header("token_id") String token_id, @Query("event_roster_id") String event_roster_id);

    @GET("/ccit_backend/event/event_rest/list")
    Call<JsonElement> EventList(@Header("token_id") String token_id);
    @GET("/ccit_backend/organization/organization_rest/list")
    Call<JsonElement> TeamList(@Header("token_id") String token_id);

    @POST("/ccit_backend/event_roster/event_roster_rest/delete")
    Call<JsonElement> DeleteEventRoster(@Body DeleteEvents body);



//    @POST("/ccit_backend/event_roster/event_roster_rest/add")
//    @FormUrlEncoded
//    Call<JsonElement>;


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
