package com.akarbowy.cakelisttest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akarbowy.cakelisttest.data.CakesRepository
import com.akarbowy.cakelisttest.domain.CakeDM
import com.akarbowy.cakelisttest.extensions.scheduleOn
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject


internal class MainViewModel @Inject constructor(
    private val cakesRepository: CakesRepository
) : ViewModel() {

    val state: LiveData<MainState>
        get() {
            if (!::_state.isInitialized) {
                _state = MutableLiveData()
                _state.value = MainState.ShowLoading
            }
            return _state
        }

    private lateinit var _state: MutableLiveData<MainState>

    private val disposables = CompositeDisposable()

    fun loadCakes() {
        if (_state.value !is MainState.ShowCakes) {
            loadData()
        }
    }

    fun refresh() {
        loadData()
    }

    fun retry() {
        _state.value = MainState.ShowLoading
        loadData()
    }

    private fun loadData() {
        cakesRepository.loadCakes()
            .scheduleOn()
            .subscribeBy(
                onSuccess = { data -> onDataLoaded(data) },
                onError = { error -> onDataLoadingError(error) }
            ).addTo(disposables)
    }

    private fun onDataLoaded(cakes: List<CakeDM>) {
        val uim = cakes.toUIM()
            .sortedBy { it.title }
            .distinct()

        if (uim.isNotEmpty()) {
            _state.value = MainState.ShowCakes(uim)
        } else {
            _state.value = MainState.ShowEmpty
        }
    }

    private fun onDataLoadingError(error: Throwable) {
        Timber.e(error)
        _state.value = MainState.ShowError
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    sealed class MainState {
        object ShowLoading : MainState()
        class ShowCakes(val uim: List<CakeUIM>) : MainState()
        object ShowError : MainState()
        object ShowEmpty : MainState()
    }
}