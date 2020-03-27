package ua.turskyi.visitedcountries.features.home

import android.os.Bundle
import ua.turskyi.visitedcountries.R
import ua.turskyi.visitedcountries.common.di.qualifiers.ViewModelInjection
import ua.turskyi.visitedcountries.common.ui.base.BaseActivity
import javax.inject.Inject

class HomeActivity : BaseActivity() {
    override fun layoutRes() = R.layout.activity_home

    @Inject
    @field:ViewModelInjection
    lateinit var viewModel: HomeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
