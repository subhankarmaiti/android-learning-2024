package com.example.myshopinglistapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://maps.googleapis.com/"

    fun create(): GeocodingApiServices {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(GeocodingApiServices::class.java)
    }
}