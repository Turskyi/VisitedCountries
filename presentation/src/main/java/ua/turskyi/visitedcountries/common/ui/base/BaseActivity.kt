package ua.turskyi.visitedcountries.common.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerAppCompatActivity
import ua.turskyi.data.db.CountriesDataBase
import ua.turskyi.visitedcountries.R

abstract class BaseActivity: DaggerAppCompatActivity(){
    @LayoutRes
    abstract fun layoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
    }
}