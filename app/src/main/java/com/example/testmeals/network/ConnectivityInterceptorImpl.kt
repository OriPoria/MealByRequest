package com.example.meals.network

import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl : ConnectivityInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}