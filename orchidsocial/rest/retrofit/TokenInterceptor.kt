package com.kecsot.orchidsocial.rest.retrofit

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

internal class TokenInterceptor : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder()
                .addQueryParameter(
                        "token",
                        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC85ODI4ZWZkNS5uZ3Jvay5pb1wvdG9sZW1uZWtlZC1zZXJ2ZXJcL3B1YmxpY1wvYXV0aGVudGljYXRlXC9mYWNlYm9vayIsImlhdCI6MTUyNDY3NTEwMSwiZXhwIjoxNTI0Njk2NzAxLCJuYmYiOjE1MjQ2NzUxMDEsImp0aSI6ImJlNWQ1ZGY5N2ViZWRmOThmOTkxNTE3NTg0OGZiMWM4Iiwic3ViIjo1fQ.wW2Y937fBXpt_1cuTEXLhykwUvJmLjLAyHL7RMO2oBc"
                ).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)

    }
}