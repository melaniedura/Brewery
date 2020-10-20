package com.melaniedura.brewery.network

import com.melaniedura.brewery.BuildConfig.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url.newBuilder().addQueryParameter("key", API_KEY).build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}
