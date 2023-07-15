package com.pd.xchange.data.respository.remote

import com.pd.xchange.data.api.CurrencyConverterApiService
import com.pd.xchange.data.dto.ErrorDto
import com.pd.xchange.data.mapper.XchangeRateDtoToXchangeRateMapper
import com.pd.xchange.domain.entity.XchangeRates
import com.pd.xchange.domain.respository.GetRatesRepository
import com.squareup.moshi.Moshi
import javax.inject.Inject

class GetRatesRepositoryImpl @Inject constructor(
    private val currencyConverterApiService: CurrencyConverterApiService,
    private val moshi: Moshi
) : GetRatesRepository {
    override suspend fun getLatestRates(appId: String): Result<XchangeRates> = runCatching {
        val response = currencyConverterApiService.getLatest(appId)
        val body = response.body()
        if (response.isSuccessful && body != null && body.rates.isNullOrEmpty().not()) {
            XchangeRateDtoToXchangeRateMapper.map(body)
        } else {
            val errorDto =
                moshi.adapter(ErrorDto::class.java)
                    .fromJson(response.errorBody()?.string().toString())
            error(errorDto?.description.orEmpty())
        }
    }
}
