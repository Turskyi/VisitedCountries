package ua.turskyi.visitedcountries

import androidx.appcompat.app.AppCompatDelegate
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

    /**
     * onCreate is called before the first screen is shown to the user.
     * It is used to setup any background tasks, running expensive setup operations in a background
     * thread to avoid delaying app start.
     */
    override fun onCreate() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate()
        setupDaggerAppComponent()
    }

    private fun setupDaggerAppComponent() = DaggerAppComponent
        .builder()
        .application(this)
        .build()
        .inject(this)
}