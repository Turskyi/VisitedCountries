package ua.turskyi.visitedcountries

import androidx.multidex.MultiDexApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ua.turskyi.visitedcountries.common.di.component.DaggerAppComponent
import javax.inject.Inject

class App: MultiDexApplication(), HasAndroidInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> = activityInjector

    override fun onCreate() {
        super.onCreate()
        setupDaggerAppComponent()
    }

    private fun setupDaggerAppComponent() {
        DaggerAppComponent.builder().application(this).build().inject(this)
    }
}