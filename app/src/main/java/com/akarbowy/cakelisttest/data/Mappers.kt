package com.akarbowy.cakelisttest.data

import com.akarbowy.cakelisttest.data.remote.CakeAM
import com.akarbowy.cakelisttest.domain.CakeDM

internal fun List<CakeAM>.toDM() = map { it.toDM() }

internal fun CakeAM.toDM() = CakeDM(title, desc, image)