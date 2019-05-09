package com.akarbowy.cakelisttest.data

import com.akarbowy.cakelisttest.data.remote.WaracleCakeApi
import com.akarbowy.cakelisttest.domain.CakeDM
import io.reactivex.Single
import javax.inject.Inject

internal class CakesRepositoryImpl @Inject constructor(
    private val api: WaracleCakeApi
) : CakesRepository {

    /**
     * TODO: in a real world we'd implement a local data source.
     * I'll keep it simple and take advantage of ViewModel (from architecture components)
     * to retain data on orientation change
     */
    override fun loadCakes(): Single<List<CakeDM>> = api.getCakes().map { it.toDM() }

}