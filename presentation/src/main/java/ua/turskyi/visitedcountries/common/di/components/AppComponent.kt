package ua.turskyi.visitedcountries.common.di.components

import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import ua.turskyi.visitedcountries.common.di.module.FragmentInjectorsModule
import ua.turskyi.visitedcountries.App
import ua.turskyi.visitedcountries.common.di.module.ActivityInjectorsModule
import ua.turskyi.visitedcountries.common.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityInjectorsModule::class,
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