package com.pd.xchange.network.creator

import com.squareup.moshi.Moshi

interface ApiServiceCreator {
    fun <T> create(
        serviceClass: Class<T>,
        baseUrlOverride: String? = null,
        moshiOverride: Moshi.Builder? = null
    ): T
}