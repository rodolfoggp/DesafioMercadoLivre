package com.desafiomercadolivre.common.resource

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider(val context: Context) {
    fun getString(@StringRes resId: Int, vararg argument: Any): String =
        context.resources.getString(resId, *argument)
}