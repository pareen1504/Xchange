package com.pd.xchange.jvm.utils

import java.util.regex.Pattern

fun String?.checkIsValidInput() =
    this != null && (Pattern.matches(Constants.NUMBERS_REGEX, this) || this.isEmpty())
