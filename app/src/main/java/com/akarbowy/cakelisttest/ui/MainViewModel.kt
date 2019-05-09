package com.akarbowy.cakelisttest.ui

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

    private val disposables = CompositeDisposable()

    fun loadCakes() {
        cakesRepository.loadCakes()
            .scheduleOn()
            .subscribeBy(
                onSuccess = { data -> onCakesLoaded(data) },
                onError = { error -> onCakesLoadingError(error) }
            ).addTo(disposables)
    }

    private fun onCakesLoaded(cakes: List<CakeDM>) {
        Timber.i("Loaded cakes: $cakes")
    }

    private fun onCakesLoadingError(error: Throwable) {
        Timber.e(error)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}