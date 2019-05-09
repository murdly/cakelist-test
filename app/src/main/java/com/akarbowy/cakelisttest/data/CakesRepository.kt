package com.akarbowy.cakelisttest.data

import com.akarbowy.cakelisttest.domain.CakeDM
import io.reactivex.Single


internal interface CakesRepository {

    fun loadCakes(): Single<List<CakeDM>>
}