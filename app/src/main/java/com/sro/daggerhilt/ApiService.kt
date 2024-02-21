package com.sro.daggerhilt
import retrofit2.http.GET

interface ApiService {


    @GET("/posts")
    suspend fun getPost(): List<Post>

}