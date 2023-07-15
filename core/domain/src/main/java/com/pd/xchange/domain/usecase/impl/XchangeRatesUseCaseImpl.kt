package com.pd.xchange.domain.usecase.impl

import com.pd.core.prefence.AppPreference
import com.pd.xchange.domain.entity.XchangeRates
import com.pd.xchange.domain.respository.GetRatesRepository
import com.pd.xchange.domain.respository.LocalDbRepository
import com.pd.xchange.domain.usecase.XchangeRatesUseCase
import com.pd.xchange.jvm.Dispatcher
import com.pd.xchange.jvm.XchangeDispatchers.*
import com.pd.xchange.jvm.config.AppConfig
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class XchangeRatesUseCaseImpl @Inject constructor(
    private val appConfig: AppConfig,
    private val appPreference: AppPreference,
    private val localDbRepository: LocalDbRepository,
    private val getRatesRepository: GetRatesRepository,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : XchangeRatesUseCase {
    override fun execute(input: String): Flow<XchangeRates?> = flow {
        val localData = localDbRepository.getAllCurrencyRates()
        emit(
            when {
                localData.rates.isNullOrEmpty()
                    .not() && appPreference.isDataStaled.not() -> localData

                else -> getRatesRepository.getLatestRates(appConfig.appId).fold(
                    onSuccess = { xchangeRates ->
                        xchangeRates?.let {
                            localDbRepository.upsertCurrencyRates(it)
                            appPreference.dataTimestamp = appConfig.currentTimeMillis
                            it.copy(isStaledData = false)
                        }
                    },
                    onFailure = {
                        localData.takeIf { it.rates.isNullOrEmpty().not() }
                            ?.copy(isStaledData = true)
                            ?: run {
                                throw IllegalArgumentException("No data to show")
                            }
                    }
                )
            }
        )
    }.flowOn(ioDispatcher)
}
