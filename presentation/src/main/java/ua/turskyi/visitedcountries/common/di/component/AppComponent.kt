package ua.turskyi.visitedcountries.common.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ua.turskyi.data.common.modules.DataModule
import ua.turskyi.visitedcountries.common.App
import ua.turskyi.visitedcountries.common.di.modules.ActivityInjectorsModule
import ua.turskyi.visitedcountries.common.di.modules.AppModule
import ua.turskyi.visitedcountries.common.di.modules.FragmentInjectorsModule
import ua.turskyi.visitedcountries.common.di.modules.RxSchedulersModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityInjectorsModule::class,
        RxSchedulersModule::class,
        DataModule::class,
        FragmentInjectorsModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun build(): AppComponent
    }
    fun inject(app: App)
}