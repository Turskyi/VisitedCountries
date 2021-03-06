package ua.turskyi.visitedcountries.features.allcountries.view.adapter

import android.util.Log
import androidx.paging.PositionalDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import ua.turskyi.domain.model.Country
import ua.turskyi.domain.usecases.GetCountriesByRangeUseCase

internal class CountriesPositionalDataSource(
    private val getCountriesByRangeUseCase: GetCountriesByRangeUseCase,
    private val compositeDisposable: CompositeDisposable
) : PositionalDataSource<Country>() {
    companion object{
        const val LOAD_RANGE = "loadRange"
    }
    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<Country>
    ) {
        val disposable = getCountriesByRangeUseCase.execute(
            params.requestedLoadSize,
            0,
            Consumer { allCountries ->
                callback.onResult(allCountries, 0)
            },
            Consumer {
                //TODO: IllegalStateException: callback.onResult/onError already called, cannot call again.
//                callback.onResult(emptyList(), 0)
                Log.d(LOAD_RANGE, "error in loadInitial :( $it")
            }
        )
        compositeDisposable.add(disposable)
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<Country>
    ) {
        Log.d(LOAD_RANGE, "${params.loadSize} \\ ${params.startPosition}")
        val disposable = getCountriesByRangeUseCase.execute(
            params.loadSize,
            params.startPosition,
            Consumer { allCountries ->
                callback.onResult(allCountries)
            },
            Consumer {
                //TODO: IllegalStateException: callback.onResult/onError already called, cannot call again.
//                callback.onResult(emptyList())
                Log.d(LOAD_RANGE, "error in loadRange :( $it ")
            }
        )
        compositeDisposable.add(disposable)
    }
}