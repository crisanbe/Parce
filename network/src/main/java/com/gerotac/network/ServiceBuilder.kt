package com.gerotac.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceBuilder {
    companion object {
        fun <T> createService(serviceType: Class<T>, url: String, headers: Map<String, String>): T {
            val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val headerInterceptor = Interceptor { chain ->
                var request = chain.request()
                val requestBuilder = request.newBuilder()
                headers.forEach { header ->
                    requestBuilder.addHeader(header.key, headers[header.key].toString())
                }
                request = requestBuilder.build()
                val response = chain.proceed(request)
                response
            }
            val okHttp =
                OkHttpClient.Builder().addInterceptor(headerInterceptor).addInterceptor(logger)
            val builder =
                Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                    .client(okHttp.build())
            return builder.build().create(serviceType)
        }
    }
}
