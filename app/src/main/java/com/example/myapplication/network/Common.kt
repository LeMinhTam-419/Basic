package com.example.shakil.kotlinjsonexample.Interface

import com.example.myapplication.network.APIService
import com.example.myapplication.retrofit.RetrofitClient


object Common {

    private val BASE_URL = "https://5ef1959c1faf160016b4d9c6.mockapi.io/foodapi/v1/"

    val retrofitService: APIService
    get() = RetrofitClient.getClient(BASE_URL).create(APIService::class.java)
}