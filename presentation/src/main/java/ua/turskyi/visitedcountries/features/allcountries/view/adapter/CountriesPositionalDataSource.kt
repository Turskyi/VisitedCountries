package ua.turskyi.visitedcountries.features.allcountries.view.adapter

import android.content.Context
import android.util.Log
import androidx.paging.PositionalDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ua.turskyi.data.db.CountriesDataBase
import ua.turskyi.domain.models.Country

internal class CountriesPositionalDataSource(
    context: Context,
    private val compositeDisposable: CompositeDisposable
) : PositionalDataSource<Country>() {

    private val database = CountriesDataBase.getInstance(context)

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<Country>
    ) {

        val disposable = database?.countryDAO()
            ?.getCountriesByRange(params.requestedLoadSize, 0)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(Schedulers.io())
            ?.subscribe({ countries ->
                callback.onResult(countries, 0)
            }, { throwable ->
                //TODO: IllegalStateException: callback.onResult/onError already called, cannot call again.
//                callback.onResult(emptyList(), 0)
                Log.d(throwable.message, "error :(")
            })
        disposable?.let {it -> compositeDisposable.add(it) }
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<Country>
    ) {
        Log.d("loadRange", "${params.loadSize} \\ ${params.startPosition}")
        val disposable = database?.countryDAO()?.getCountriesByRange(
            params.loadSize,
            params.startPosition
        )
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(Schedulers.io())
            ?.subscribe({ countries ->
                callback.onResult(countries)
            }, { throwable ->
                //TODO: IllegalStateException: callback.onResult/onError already called, cannot call again.
//                callback.onResult(emptyList())
                Log.d(throwable.message, "error :(")
            })
        disposable?.let {it-> compositeDisposable.add(it) }
    }
}