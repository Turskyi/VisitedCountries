package ua.turskyi.visitedcountries.features.allcountries.view.adapter

import android.util.Log
import androidx.paging.PositionalDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import ua.turskyi.domain.model.Country
import ua.turskyi.domain.usecases.GetCountriesFromDbUseCase

internal class CountriesPositionalDataSource(
    private val countriesFromDbUseCase: GetCountriesFromDbUseCase,
    private val compositeDisposable: CompositeDisposable
) : PositionalDataSource<Country>() {
    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<Country>
    ) {
        val disposable = countriesFromDbUseCase.execute(
            Consumer { allCountries ->
                callback.onResult(allCountries, 0)
            },
            Consumer {
                Log.d(it, "error :(")
            }
        )
        disposable.let { it -> compositeDisposable.add(it) }
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<Country>
    ) {
        val disposable = countriesFromDbUseCase.execute(
            Consumer { allCountries ->
                callback.onResult(allCountries)
            },
            Consumer {
                Log.d(it, "error :(")
            }
        )

        Log.d("loadRange", "${params.loadSize} \\ ${params.startPosition}")
        disposable.let { it -> compositeDisposable.add(it) }
    }
}