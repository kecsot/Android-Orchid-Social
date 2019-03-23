package com.kecsot.orchidsocial.rest.retrofit

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.kecsot.orchidsocial.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class ApiClient {

    fun getClient(url: String): Retrofit {

        val gsonBuilder = GsonBuilder()
                .setLenient()
                .serializeNulls()

        gsonBuilder.registerTypeAdapter(Date::class.java, JsonDeserializer { json, typeOfT, context ->
            try {
                return@JsonDeserializer Date(json.asJsonPrimitive.asLong)
            } catch (e: Exception) {
                return@JsonDeserializer null
            }
        })

        val gson = gsonBuilder.create()

        val builder = OkHttpClient.Builder()
                .addInterceptor(TokenInterceptor())


        //Logging
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
        }

        builder.writeTimeout(TIMEOUT_SEC.toLong(), TimeUnit.SECONDS)
        builder.readTimeout(TIMEOUT_SEC.toLong(), TimeUnit.SECONDS)
        builder.connectTimeout(TIMEOUT_SEC.toLong(), TimeUnit.SECONDS)

        return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build()
    }

    companion object {
        private val TIMEOUT_SEC = 60
    }


}
