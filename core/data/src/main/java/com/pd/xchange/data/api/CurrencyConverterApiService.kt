package com.pd.xchange.data.api

import com.pd.xchange.data.dto.XchangeRatesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyConverterApiService {

    @GET("/api/latest.json")
    suspend fun getLatest(
        @Query(value = "app_id") apiId: String,
    ): Response<XchangeRatesDTO>
}