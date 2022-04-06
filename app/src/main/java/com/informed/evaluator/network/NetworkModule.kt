package com.informed.evaluator.network

import android.content.Context
import com.informed.evaluator.BuildConfig
import com.informed.evaluator.common.BaseApp
import com.informed.evaluator.network.adapter.NullToEmptyStringAdapter
import com.informed.evaluator.network.adapter.StringToBooleanAdapter
import com.informed.evaluator.preference.ConstantKeys
import com.informed.evaluator.preference.SharedPreference

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


object NetworkModule {

    private val variableUrl="/api/v1/"

    private val baseUrl = "http://34.74.224.155"

    private val ngrokUrl=""

    private val compUrl= baseUrl+ variableUrl

    fun makeApiService(): Retrofit = Retrofit.Builder()
        .baseUrl(compUrl)
        .client(okHttpClient(BaseApp.getAppContext()).build())
        .addConverterFactory(MoshiConverterFactory.create(moshiFactory()).asLenient())
        .build()

    fun makeApiServicewithAuth(): Retrofit = Retrofit.Builder()
        .baseUrl(compUrl)
        .client(okHttpBuilder2(BaseApp.getAppContext()))
        .addConverterFactory(MoshiConverterFactory.create(moshiFactory()).asLenient())
        .build()

    fun moshiFactory(): Moshi {
        return Moshi.Builder()
            .add(StringToBooleanAdapter())
            .add(NullToEmptyStringAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
    }

     fun okHttpClient(applicationContext: BaseApp) =
        okHttpBuilder(applicationContext)


    private fun okHttpBuilder(context: Context) = OkHttpClient.Builder()
        .addInterceptor(makeLoggingInterceptor())
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)


    private fun makeLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

    private fun okHttpBuilder2(context: Context) = OkHttpClient.Builder()
        .addNetworkInterceptor(interceptor)
        .addNetworkInterceptor(makeLoggingInterceptor())
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
            .build()


    val interceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder().addHeader(
            "Authorization",
            "Bearer " + SharedPreference().getValueString(ConstantKeys.ACCESS_TOKEN).toString()
        ).cacheControl(CacheControl.FORCE_NETWORK).build()
        chain.proceed(newRequest)
    }

    fun convertResponse(className: Any, value: Any): Any {
        val moshi = moshiFactory()
        val jsonAdapter =
            moshi.adapter(className as Class<*>)
        if (value is Buffer)
            return jsonAdapter.fromJson(value)!!
        else
            return jsonAdapter.fromJsonValue(value)!!
    }

}