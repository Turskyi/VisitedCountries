package ua.turskyi.domain.usecases

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import ua.turskyi.domain.model.Country

interface GetCountriesUseCase {
    fun execute(
        successConsumer: Consumer<List<Country>>,
        errorConsumer: Consumer<String>
    ): Disposable
}