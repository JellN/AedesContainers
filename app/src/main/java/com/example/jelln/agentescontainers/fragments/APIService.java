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
                    "Authorization:key=AAAAoFArSp8:APA91bGnUbPhFWUYiaDfh95RrvXs_p9QPchN1Adc_xh3KcCaC_lsjaekPj2gYVCtM2f1Vp8uvnWLVRBKYpVRQz" +
                            "Obsc81FiIceKAUqVs0DGDpOFCebb5kHEX28KJulsqk1v2Tz-klyxMH"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
