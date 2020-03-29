package ua.turskyi.visitedcountries.features.allcountries.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.paging.PositionalDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ua.turskyi.domain.models.Country
import java.util.*

internal class CountriesPositionalDataSource(
    context: Context,
    private val compositeDisposable: CompositeDisposable
) : PositionalDataSource<Country>() {

//    private val database = CountriesDataBase.getInstance(context)

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<Country>
    ) {
//        val disposable = database?.countryDAO()
//            ?.getCountriesByRange(params.requestedLoadSize, 0)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(Schedulers.io())
//            ?.subscribe({ countries ->
        val allCountries  = Collections.nCopies(500, Country(id = 0 ,name = "DEFAULT",flag = "", visited = false))
                callback.onResult(allCountries, 0)
//            }, { throwable ->
//                //TODO: IllegalStateException: callback.onResult/onError already called, cannot call again.
////                callback.onResult(emptyList(), 0)
//                Log.d(throwable, "error :(")
//            })
//        disposable?.let {it -> compositeDisposable.add(it) }
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<Country>
    ) {
//        Log.d("loadRange", "${params.loadSize} \\ ${params.startPosition}")
//        val disposable = database?.countryDAO()?.getCountriesByRange(
//            params.loadSize,
//            params.startPosition
//        )
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(Schedulers.io())
//            ?.subscribe({ countries ->
        val allCountries  = Collections.nCopies(500, Country(id = 0 ,name = "DEFAULT",flag = "", visited = false))
                callback.onResult(allCountries)
//            }, { throwable ->
//                //TODO: IllegalStateException: callback.onResult/onError already called, cannot call again.
////                callback.onResult(emptyList())
//                Log.d(throwable, "error :(")
//            })
//        disposable?.let {it-> compositeDisposable.add(it) }
    }
}