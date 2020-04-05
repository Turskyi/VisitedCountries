package ua.turskyi.domain.usecases

import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import org.reactivestreams.Subscription
import ua.turskyi.domain.common.di.qualifiers.schedulers.IoScheduler
import ua.turskyi.domain.common.di.qualifiers.schedulers.MainScheduler
import ua.turskyi.domain.model.Country
import ua.turskyi.domain.repositories.CountriesRepository
import javax.inject.Inject

class GetNotVisitedNumUseCase @Inject constructor(
    private val countriesRepository: CountriesRepository,
    @IoScheduler private val subscribeOnScheduler: Scheduler,
    @MainScheduler private val observeOnScheduler: Scheduler
) {
    fun execute(
        subscriptionConsumer: Consumer<Disposable>,
        successConsumer: Consumer<Int>,
        errorConsumer: Consumer<String>
    ): Disposable {
        return countriesRepository.getCountNotVisitedRx()
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .doOnSubscribe {
                subscriptionConsumer.accept(it)
            }
            .subscribe({
                successConsumer.accept(it)
            }, {
                errorConsumer.accept(it.message)
            })
    }
}