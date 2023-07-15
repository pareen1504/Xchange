package com.pd.xchange.jvm.config

interface AppConfig {
    val appId: String
    val baseUrl: String
    val currentTimeMillis: Long
}