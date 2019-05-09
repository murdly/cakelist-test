package com.akarbowy.cakelisttest.data.remote

import io.reactivex.Single
import retrofit2.http.GET

internal interface WaracleCakeApi {

    @GET("waracle_cake-android-client")
    fun getCakes(): Single<List<CakeAM>>

    companion object {
        const val BASE_URL =
            "https://gist.githubusercontent.com/t-reed/739df99e9d96700f17604a3971e701fa/raw/1d4dd9c5a0ec758ff5ae92b7b13fe4d57d34e1dc/"
    }

}