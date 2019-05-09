package com.akarbowy.cakelisttest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.akarbowy.cakelisttest.data.CakesRepository
import com.akarbowy.cakelisttest.domain.CakeDM
import com.akarbowy.cakelisttest.rules.InstantRxSchedulerRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify


class MainViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var instantRxSchedulerRule = InstantRxSchedulerRule()

    @Captor
    private val stateArgumentCaptor = ArgumentCaptor.forClass(MainViewModel.MainState::class.java)

    private val stateObserver: Observer<MainViewModel.MainState> = mock()

    private val mockCakesRepository: CakesRepository = mock()

    private lateinit var underTest: MainViewModel

    @Before
    fun setUp() {
        underTest = MainViewModel(mockCakesRepository)

        underTest.state.observeForever(stateObserver)
    }

    @Test
    fun showCakesWhenSuccessfulResponse() {
        reset(stateObserver)

        `when`(mockCakesRepository.loadCakes()) doReturn Single.just(CAKES_SUCCESS)

        underTest.loadCakes()

        verify(mockCakesRepository).loadCakes()
        verify(stateObserver).onChanged(stateArgumentCaptor.capture())
        assert(stateArgumentCaptor.value is MainViewModel.MainState.ShowCakes)
        val cakes = stateArgumentCaptor.value as MainViewModel.MainState.ShowCakes
        assert(cakes.uim.first().title.toLowerCase() == "namea")
        assert(cakes.uim.size == 2)
    }

    @Test
    fun showEmptyWhenSuccessfulEmptyResponse() {
        reset(stateObserver)

        `when`(mockCakesRepository.loadCakes()) doReturn Single.just(CAKES_SUCCESS_EMPTY)

        underTest.loadCakes()

        verify(mockCakesRepository).loadCakes()
        verify(stateObserver).onChanged(stateArgumentCaptor.capture())
        assert(stateArgumentCaptor.value is MainViewModel.MainState.ShowEmpty)
    }

    @Test
    fun showErrorWhenNoSuccessfulResponse() {
        reset(stateObserver)

        `when`(mockCakesRepository.loadCakes()) doReturn Single.error(CAKES_ERROR)

        underTest.loadCakes()

        verify(mockCakesRepository).loadCakes()
        verify(stateObserver).onChanged(stateArgumentCaptor.capture())
        assert(stateArgumentCaptor.value is MainViewModel.MainState.ShowError)
    }

    companion object {
        private val CAKES_SUCCESS = listOf(
            CakeDM("nameB", "", ""),
            CakeDM("nameA", "", ""),
            CakeDM("nameA", "", "")
        )

        private val CAKES_SUCCESS_EMPTY = emptyList<CakeDM>()

        private val CAKES_ERROR = Throwable()
    }

}

