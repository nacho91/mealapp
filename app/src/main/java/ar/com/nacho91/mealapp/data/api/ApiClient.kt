package ar.com.nacho91.mealapp.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient(baseUrl: String) {

    private val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .client(createHttpClient(60L, true))
            .baseUrl("$baseUrl/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createHttpClient(timeout: Long, logRequest: Boolean) : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()

        if (logRequest)
            httpClient.interceptors().add(logging)

        httpClient.connectTimeout(timeout, TimeUnit.SECONDS)
        httpClient.readTimeout(timeout, TimeUnit.SECONDS)
        httpClient.writeTimeout(timeout, TimeUnit.SECONDS)

        return httpClient.build()
    }

    fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}