package com.akarbowy.cakelisttest.ui

import com.akarbowy.cakelisttest.domain.CakeDM


internal fun List<CakeDM>.toUIM() = map { it.toUIM() }

internal fun CakeDM.toUIM(): CakeUIM {
    if (title.isNullOrBlank()) throw IllegalStateException("Cake must have a name")

    return CakeUIM(title.capitalize(), description.orEmpty(), imageUrl)
}