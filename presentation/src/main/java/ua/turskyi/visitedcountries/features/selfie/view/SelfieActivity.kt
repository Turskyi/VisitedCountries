package ua.turskyi.visitedcountries.features.selfie.view

import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_selfie.*
import ua.turskyi.visitedcountries.R
import ua.turskyi.visitedcountries.common.di.qualifiers.ViewModelInjection
import ua.turskyi.visitedcountries.common.ui.base.BaseActivity
import ua.turskyi.visitedcountries.features.selfie.viewmodel.SelfieActivityViewModel
import javax.inject.Inject

class SelfieActivity : BaseActivity() {
    override fun layoutRes() = R.layout.activity_selfie

    @Inject
    @field:ViewModelInjection
    lateinit var viewModel: SelfieActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListeners()
    }
    private fun initView() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorBlack)
    }

    private fun initListeners() {
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}