package com.example.myapplication.network

import com.example.myapplication.model.Food

import retrofit2.Call
import retrofit2.http.GET

interface APIService {

  @GET("foods")
    fun getMovieList(): Call<MutableList<Food>>
}