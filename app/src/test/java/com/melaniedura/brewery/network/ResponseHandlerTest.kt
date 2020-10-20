package com.melaniedura.brewery.network

import com.melaniedura.brewery.model.StyleDomainModel
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

@RunWith(JUnit4::class)
class ResponseHandlerTest {
    private lateinit var responseHandler: ResponseHandler

    @Before
    fun setUp() {
        responseHandler = ResponseHandler()
    }

    @Test
    fun `when exception is HttpException and code is 400 then return Bad request message`() {
        val httpException = HttpException(Response.error<List<StyleDomainModel>>(HttpURLConnection.HTTP_BAD_REQUEST, mock()))
        val result = responseHandler.handleException<List<StyleDomainModel>>(httpException)
        assertEquals("Bad request", result.message)
    }

    @Test
    fun `when exception is HttpException and code is 401 then return Unauthorized error message`() {
        val httpException = HttpException(Response.error<List<StyleDomainModel>>(HttpURLConnection.HTTP_UNAUTHORIZED, mock()))
        val result = responseHandler.handleException<List<StyleDomainModel>>(httpException)
        assertEquals("Unauthorized", result.message)
    }

    @Test
    fun `when exception is HttpException and code is 403 then return Forbidden error message`() {
        val httpException = HttpException(Response.error<List<StyleDomainModel>>(HttpURLConnection.HTTP_FORBIDDEN, mock()))
        val result = responseHandler.handleException<List<StyleDomainModel>>(httpException)
        assertEquals("Forbidden", result.message)
    }

    @Test
    fun `when exception is HttpException and code is 404 then return Not found error message`() {
        val httpException = HttpException(Response.error<List<StyleDomainModel>>(HttpURLConnection.HTTP_NOT_FOUND, mock()))
        val result = responseHandler.handleException<List<StyleDomainModel>>(httpException)
        assertEquals("Not found", result.message)
    }

    @Test
    fun `when exception is HttpException and code is 503 then return Not found error message`() {
        val httpException = HttpException(Response.error<List<StyleDomainModel>>(HttpURLConnection.HTTP_UNAVAILABLE, mock()))
        val result = responseHandler.handleException<List<StyleDomainModel>>(httpException)
        assertEquals("Service Unavailable", result.message)
    }

    @Test
    fun `when exception is SocketTimeoutException then return Timeout error message`() {
        val socketTimeoutException = SocketTimeoutException()
        val result = responseHandler.handleException<List<StyleDomainModel>>(socketTimeoutException)
        assertEquals("Timeout", result.message)
    }

    @Test
    fun `when exception is IOException then return No connection error message`() {
        val ioException = IOException()
        val result = responseHandler.handleException<List<StyleDomainModel>>(ioException)
        assertEquals("No Connection", result.message)
    }
}
