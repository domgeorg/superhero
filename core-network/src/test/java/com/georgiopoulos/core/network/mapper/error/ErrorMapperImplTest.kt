package com.georgiopoulos.core.network.mapper.error

import com.georgiopoulos.core.model.error.ErrorModel
import com.georgiopoulos.core.model.error.NetworkErrorModel
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection

class ErrorMapperImplTest {
    @Test
    fun `Given IOException, When mapError is called, Then return Network ErrorModel`() {
        // Given
        val mapper = ErrorMapperImpl()
        val exception = IOException("Network error")

        // When
        val result = mapper.mapError(exception)

        // Then
        assertEquals(result, NetworkErrorModel.Network)
    }

    @Test
    fun `Given HttpException with 404 code, When mapError is called, Then return NotFound ErrorModel`() {
        // Given
        val mapper = ErrorMapperImpl()
        val exception = createHttpException(HttpURLConnection.HTTP_NOT_FOUND)

        // When
        val result = mapper.mapError(exception)

        // Then
        assertEquals(result, NetworkErrorModel.NotFound)
    }

    @Test
    fun `Given HttpException with 401 code, When mapError is called, Then return NotAuthorized ErrorModel`() {
        // Given
        val mapper = ErrorMapperImpl()
        val exception = createHttpException(HttpURLConnection.HTTP_UNAUTHORIZED)

        // When
        val result = mapper.mapError(exception)

        // Then
        assertEquals(result, NetworkErrorModel.NotAuthorized)
    }

    @Test
    fun `Given HttpException with 503 code, When mapError is called, Then return ServiceUnavailable ErrorModel`() {
        // Given
        val mapper = ErrorMapperImpl()
        val exception = createHttpException(HttpURLConnection.HTTP_UNAVAILABLE)

        // When
        val result = mapper.mapError(exception)

        // Then
        assertEquals(result, NetworkErrorModel.ServiceUnavailable)
    }

    @Test
    fun `Given HttpException with 500 code, When mapError is called, Then return ServiceNotWorking ErrorModel`() {
        // Given
        val mapper = ErrorMapperImpl()
        val exception = createHttpException(HttpURLConnection.HTTP_INTERNAL_ERROR)

        // When
        val result = mapper.mapError(exception)

        // Then
        assertEquals(result, NetworkErrorModel.ServiceNotWorking)
    }

    @Test
    fun `Given HttpException with unknown code, When mapError is called, Then return Unknown ErrorModel`() {
        // Given
        val mapper = ErrorMapperImpl()
        val exception = createHttpException(999)

        // When
        val result = mapper.mapError(exception)

        // Then
        assertEquals(result, NetworkErrorModel.Unknown)
    }

    @Test
    fun `Given unknown exception, When mapError is called, Then return Unknown ErrorModel`() {
        // Given
        val mapper = ErrorMapperImpl()
        val exception = RuntimeException("Unknown error")

        // When
        val result = mapper.mapError(exception)

        // Then
        assertEquals(result, ErrorModel.UnknownErrorModel)
    }

    private fun createHttpException(code: Int): HttpException {
        val response = Response.error<Any>(code, ResponseBody.create(null, ""))
        return HttpException(response)
    }
}