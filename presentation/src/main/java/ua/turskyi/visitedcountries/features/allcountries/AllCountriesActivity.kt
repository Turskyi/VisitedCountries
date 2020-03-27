package ua.turskyi.visitedcountries.features.allcountries

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.intentFor
import ua.turskyi.visitedcountries.R
import ua.turskyi.visitedcountries.common.di.qualifiers.ViewModelInjection
import ua.turskyi.visitedcountries.common.ui.base.BaseActivity
import javax.inject.Inject

class AllCountriesActivity : BaseActivity() {
    override fun layoutRes() = R.layout.activity_all_countries

    companion object {
        fun getIntent(activity: Activity) = activity.intentFor<AllCountriesActivity>()
    }

    @Inject
    @field:ViewModelInjection
    lateinit var viewModel: AllCountriesActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}