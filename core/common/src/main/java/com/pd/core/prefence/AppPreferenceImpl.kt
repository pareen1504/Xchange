package com.pd.core.prefence

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val FIRST_LAUNCH = "first_launch"
private const val DATA_TIMESTAMP = "data_timestamp"
const val THIRTY_MINUTES_IN_MILLIS = 1800000L
private const val DEFAULT_TIME_IN_MILLIS = 0L

class AppPreferenceImpl @Inject constructor(
    @ApplicationContext context: Context
) : AppPreference {

    private val sharedPreferences =
        context.getSharedPreferences("${context.packageName}.properties", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    override var dataTimestamp: Long
        get() = sharedPreferences.getLong(DATA_TIMESTAMP, DEFAULT_TIME_IN_MILLIS)
        set(value) = editor.putLong(DATA_TIMESTAMP, value).apply()

    override val isDataStaled: Boolean
        get() = timeSinceLastUpdateMillis > THIRTY_MINUTES_IN_MILLIS

    override val isDataEmpty: Boolean
        get() = timeSinceLastUpdateMillis == DEFAULT_TIME_IN_MILLIS

    private val timeSinceLastUpdateMillis: Long
        get() {
            return if (dataTimestamp != DEFAULT_TIME_IN_MILLIS) {
                System.currentTimeMillis() - dataTimestamp
            } else {
                DEFAULT_TIME_IN_MILLIS
            }
        }
}