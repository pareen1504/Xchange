package com.pd.xchange.jvm

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val xchangeDispatchers: XchangeDispatchers)

enum class XchangeDispatchers {
    IO
}