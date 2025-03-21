package com.desafiomercadolivre.search.domain

class IsValidQueryUseCase {
    operator fun invoke(query: String) = query.isNotBlank()
}
