package com.sro.daggerhilt

import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getPost(): List<Post> = apiService.getPost()


}