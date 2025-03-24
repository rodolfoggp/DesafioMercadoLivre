package com.desafiomercadolivre.common.resource

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider(val context: Context) {
    fun getString(@StringRes resId: Int, argument: String): String =
        context.resources.getString(resId, argument)
}