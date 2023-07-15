package com.pd.xchange.jvm.usecase

interface SuspendingUseCase<in Input, out Output> {
    fun execute(input: Input): Output
}