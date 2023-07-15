package com.pd.core.prefence

interface AppPreference {
    var dataTimestamp: Long
    val isDataStaled: Boolean
    val isDataEmpty: Boolean
}