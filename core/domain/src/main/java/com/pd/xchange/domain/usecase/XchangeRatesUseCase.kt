package com.pd.xchange.domain.usecase

import com.pd.xchange.domain.entity.XchangeRates
import com.pd.xchange.jvm.usecase.SuspendingUseCase
import kotlinx.coroutines.flow.Flow

interface XchangeRatesUseCase : SuspendingUseCase<String, Flow<XchangeRates?>>