package ua.turskyi.data.usecases

import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import ua.turskyi.domain.common.di.qualifiers.schedulers.IoScheduler
import ua.turskyi.domain.common.di.qualifiers.schedulers.MainScheduler
import ua.turskyi.domain.model.Country
import ua.turskyi.domain.repository.CountriesRepository
import ua.turskyi.domain.usecases.GetCountriesUseCase
import javax.inject.Inject

class GetCountriesUseCaseImpl @Inject constructor(
    private val countriesRepository: CountriesRepository,
    @IoScheduler private val subscribeOnScheduler: Scheduler,
    @MainScheduler private val observeOnScheduler: Scheduler
) : GetCountriesUseCase {
    override
    fun execute(
        successConsumer: Consumer<List<Country>>,
        errorConsumer: Consumer<String>
    ): Disposable {
        return countriesRepository.getCountriesFromDb()
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe({
                successConsumer.accept(it)
            }, {
                errorConsumer.accept(it.message)
            })
    }
}