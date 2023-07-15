package com.pd.xchange.config

import com.pd.xchange.core.common.BuildConfig
import com.pd.xchange.jvm.config.AppConfig
import javax.inject.Inject

class AppConfigImpl @Inject constructor() : AppConfig {
    override val appId: String
        get() = BuildConfig.app_id
    override val baseUrl: String
        get() = BuildConfig.base_url

    override val currentTimeMillis: Long
        get() = System.currentTimeMillis()
}