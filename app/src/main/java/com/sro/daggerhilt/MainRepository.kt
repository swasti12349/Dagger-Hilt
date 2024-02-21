package com.sro.daggerhilt

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val impl: ApiServiceImpl) {

    fun getPost(): Flow<List<Post>> {

        return flow<List<Post>> {
            emit(impl.getPost())
        }.flowOn(Dispatchers.IO)

    }

}