package ua.turskyi.visitedcountries.features.allcountries.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.paging.PositionalDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import ua.turskyi.domain.models.Country
import ua.turskyi.domain.usecase.GetCountriesUseCase
import java.util.*

internal class CountriesPositionalDataSource(
    context: Context,
    private val countriesUseCase: GetCountriesUseCase,
    private val compositeDisposable: CompositeDisposable
) : PositionalDataSource<Country>() {

//    private val database = CountriesDataBase.getInstance(context)

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<Country>
    ) {
        val disposable = countriesUseCase.execute(
            Consumer { allCountries ->
                callback.onResult(allCountries, 0)
            },
            Consumer {
               Log.d(it, "error :(")
            }
        )

//        val disposable = database?.countryDAO()
//            ?.getCountriesByRange(params.requestedLoadSize, 0)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(Schedulers.io())
//            ?.subscribe({ countries ->
//                callback.onResult(countries, 0)
//            }, { throwable ->
//                //TODO: IllegalStateException: callback.onResult/onError already called, cannot call again.
////                callback.onResult(emptyList(), 0)
//                Log.d(throwable, "error :(")
//            })
        disposable?.let {it -> compositeDisposable.add(it) }
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<Country>
    ) {

        val disposable = countriesUseCase.execute(
            Consumer { allCountries ->
                callback.onResult(allCountries)
            },
            Consumer {
                Log.d(it, "error :(")
            }
        )


        Log.d("loadRange", "${params.loadSize} \\ ${params.startPosition}")
//        val disposable = database?.countryDAO()?.getCountriesByRange(
//            params.loadSize,
//            params.startPosition
//        )
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(Schedulers.io())
//            ?.subscribe({ countries ->
//                callback.onResult(countries)
//            }, { throwable ->
//                //TODO: IllegalStateException: callback.onResult/onError already called, cannot call again.
////                callback.onResult(emptyList())
//                Log.d(throwable, "error :(")
//            })
        disposable?.let {it-> compositeDisposable.add(it) }
    }
}