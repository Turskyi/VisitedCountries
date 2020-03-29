package ua.turskyi.visitedcountries.common.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ua.turskyi.domain.common.di.qualifiers.schedulers.ComputationScheduler
import ua.turskyi.domain.common.di.qualifiers.schedulers.IoScheduler
import ua.turskyi.domain.common.di.qualifiers.schedulers.MainScheduler

@Module
class RxSchedulersModule {
    @Provides
    @MainScheduler
   internal fun mainThread() = AndroidSchedulers.mainThread()

    @Provides
    @IoScheduler
    internal fun ioThread() = Schedulers.io()

    @Provides
    @ComputationScheduler
    internal fun computationThread() = Schedulers.computation()
}