package com.desafiomercadolivre.common.resource

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes resId: Int, vararg argument: Any): String
}
