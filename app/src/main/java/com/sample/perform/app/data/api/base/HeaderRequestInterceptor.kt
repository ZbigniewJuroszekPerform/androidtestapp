
package com.sample.perform.app.data.api.base

import okhttp3.Interceptor
import okhttp3.Response

class HeaderRequestInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
                .addHeader("Referer" , "www.performgroup.com")
                .method(original.method(), original.body())
                .build()
        return chain.proceed(request)
    }
}

