package com.example.jetmoviesapp.di

import com.example.jetmoviesapp.common.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val interceptedUrl = request.url.newBuilder().addQueryParameter(name = "api_key", Constants.API_KEY).build()

        val modifiedRequest = request.newBuilder().url(interceptedUrl).build()
        return chain.proceed(modifiedRequest)
    }
}
