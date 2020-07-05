package com.saba21.demo.data.api.interceptor

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val requestUrl = chain.request().url()
        addApiKey(requestUrl, requestBuilder)
        return chain.proceed(requestBuilder.build())
    }

    private fun addApiKey(
        requestUrl: HttpUrl,
        requestBuilder: Request.Builder
    ): Request.Builder {
        val urlBuilder = requestUrl.newBuilder()
        urlBuilder.addQueryParameter("api_key", apiKey)
        requestBuilder.url(urlBuilder.build())
        return requestBuilder
    }

}