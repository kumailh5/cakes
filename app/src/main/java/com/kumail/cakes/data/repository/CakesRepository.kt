package com.kumail.cakes.data.repository

import com.kumail.cakes.data.model.Cake
import com.kumail.cakes.network.ApiResponse
import javax.inject.Inject

/**
 * Created by kumailhussain on 16/11/2021.
 */
interface CakesRepository {
    suspend fun getCakesList(): ApiResponse<List<Cake>>
}

class CakesRepositoryImpl @Inject constructor(private val cakesApi: CakesApi) :
    CakesRepository {

    override suspend fun getCakesList(): ApiResponse<List<Cake>> =
        try {
            ApiResponse.create(cakesApi.getCakesList())
        } catch (e: Exception) {
            ApiResponse.ExceptionError(e)
        }
}