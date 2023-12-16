package com.georgiopoulos.core.network.mapper.error

import com.georgiopoulos.core.model.error.ErrorModel
import com.georgiopoulos.core.model.error.NetworkErrorModel.Network
import com.georgiopoulos.core.model.error.NetworkErrorModel.NotAuthorized
import com.georgiopoulos.core.model.error.NetworkErrorModel.NotFound
import com.georgiopoulos.core.model.error.NetworkErrorModel.ServiceNotWorking
import com.georgiopoulos.core.model.error.NetworkErrorModel.ServiceUnavailable
import com.georgiopoulos.core.model.error.NetworkErrorModel.Unknown
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ErrorMapperImpl @Inject constructor() : ErrorMapper {

    override fun mapError(exception: Throwable): ErrorModel {
        return when (exception) {
            is IOException -> Network
            is HttpException -> {
                when (exception.code()) {
                    // 404
                    HttpURLConnection.HTTP_NOT_FOUND -> NotFound
                    // 401
                    HttpURLConnection.HTTP_UNAUTHORIZED -> NotAuthorized
                    // 503
                    HttpURLConnection.HTTP_UNAVAILABLE -> ServiceUnavailable
                    // 500
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> ServiceNotWorking
                    else -> Unknown
                }
            }

            else -> ErrorModel.UnknownErrorModel
        }
    }
}
