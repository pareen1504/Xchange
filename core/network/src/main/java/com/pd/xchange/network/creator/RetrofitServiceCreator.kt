package com.pd.xchange.network.creator

import com.pd.xchange.jvm.config.AppConfig
import com.squareup.moshi.Moshi
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject


internal class RetrofitServiceCreator @Inject constructor(
    private val appConfig: AppConfig,
    moshi: Moshi,
) : ApiServiceCreator {
    private val okHttpClient = OkHttpClient.Builder().apply {
        connectionPool(
            ConnectionPool(
                maxIdleConnections = 5,
                keepAliveDuration = 5,
                timeUnit = TimeUnit.SECONDS
            )
        )
        addInterceptor(
            HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        )
    }.build()

    private val defaultRemoteMoshi = moshi.newBuilder().build()

    override fun <T> create(
        serviceClass: Class<T>,
        baseUrlOverride: String?,
        moshiOverride: Moshi.Builder?,
    ): T = createRetrofitBuilder(moshiOverride, baseUrlOverride).build().create(serviceClass)

    private fun createRetrofitBuilder(moshiOverride: Moshi.Builder?, baseUrlOverride: String?) =
        with(Retrofit.Builder()) {
            client(okHttpClient)
            addConverterFactory(
                MoshiConverterFactory.create(
                    moshiOverride?.build() ?: defaultRemoteMoshi
                )
            )
            baseUrl(baseUrlOverride ?: appConfig.baseUrl)
        }
}
