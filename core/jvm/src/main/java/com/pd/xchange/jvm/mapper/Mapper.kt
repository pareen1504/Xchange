package com.pd.xchange.jvm.mapper

/**
 * Helping interface to Map Dto to Entity in suspending manner
 *
 * @param I input type
 * @param O output type
 */
interface Mapper<in I, out O> {
    suspend fun map(input: I): O
}