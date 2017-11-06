package com.emmanuelkehinde.ssetest;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by emmanuel.kehinde on 23/10/2017.
 */

public interface APIService {

    @POST("message")
    @FormUrlEncoded
    Call<JsonObject> send(@Field("msg") String message,
                          @Field("sender") String sender);

    @POST("messages/clear")
    Call<String> clearMessages();
}
