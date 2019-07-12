package com.example.jelln.agentescontainers.fragments;

import com.example.jelln.agentescontainers.Notifications.MyResponse;
import com.example.jelln.agentescontainers.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key="coloque sua api service aqui"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
