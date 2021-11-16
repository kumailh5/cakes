package com.kumail.cakes.network

import com.kumail.cakes.data.model.ErrorResponse
import com.kumail.cakes.util.parseErrorJson
import retrofit2.Response

/**
 * Created by kumailhussain on 16/11/2021.
 */
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()

                if (body == null || response.code() == 204) {
                    Empty()
                } else {
                    Success(body)
                }
            } else {
                val message = response.errorBody()?.string()
                val errorMessage = if (message.isNullOrEmpty()) {
                    ErrorResponse(response.message(), "", 0)
                } else {
                    message.parseErrorJson()
                }
                NetworkError(errorMessage)
            }
        }
    }

    class Success<T>(val data: T) : ApiResponse<T>()
    class Empty<T> : ApiResponse<T>()
    class NetworkError<T>(val errorResponse: ErrorResponse) : ApiResponse<T>()
    class ExceptionError<T>(val errorResponse: Exception) : ApiResponse<T>()
}