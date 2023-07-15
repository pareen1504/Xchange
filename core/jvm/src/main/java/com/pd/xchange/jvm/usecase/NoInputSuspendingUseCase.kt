package com.pd.xchange.jvm.usecase

interface NoInputSuspendingUseCase<out Output> {
    fun execute(): Output
}