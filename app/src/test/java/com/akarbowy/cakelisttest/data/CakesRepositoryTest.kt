package com.akarbowy.cakelisttest.data

import com.akarbowy.cakelisttest.data.remote.WaracleCakeApi
import com.akarbowy.cakelisttest.rules.InstantRxSchedulerRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`


class CakesRepositoryTest {

    @Rule
    @JvmField
    var instantRxSchedulerRule = InstantRxSchedulerRule()

    private val mockApi: WaracleCakeApi = mock()

    private lateinit var underTest: CakesRepository

    @Before
    fun setUp() {
        underTest = CakesRepositoryImpl(mockApi)

        `when`(mockApi.getCakes()).thenReturn(Single.just(emptyList()))
    }

    @Test
    fun loadsCakes() {
        underTest.loadCakes().subscribe()

        verify(mockApi).getCakes()
    }

}