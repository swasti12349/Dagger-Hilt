package com.sro.daggerhilt

sealed class ApiState {


    object loading : ApiState()
    class failure(val msg: Throwable) : ApiState()
    class Success(val data: List<Post>) : ApiState()
    object Empty : ApiState()
}
