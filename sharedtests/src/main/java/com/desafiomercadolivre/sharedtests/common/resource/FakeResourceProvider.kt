package com.desafiomercadolivre.sharedtests.common.resource

import com.desafiomercadolivre.common.resource.ResourceProvider

class FakeResourceProvider: ResourceProvider {
    override fun getString(resId: Int, vararg argument: Any): String {
        return ""
    }
}