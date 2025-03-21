package com.desafiomercadolivre.architecture.extensions

import org.koin.core.context.GlobalContext
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named

inline fun <reified T : Any> getFromKoin(name: String? = null): T {
    val qualifier = name?.let { named(name) }
    return GlobalContext.get().get(qualifier)
}

inline fun <reified T : Any> inject(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
    noinline parameters: ParametersDefinition? = null
): Lazy<T> = lazy(mode) { GlobalContext.get().get(qualifier, parameters) }