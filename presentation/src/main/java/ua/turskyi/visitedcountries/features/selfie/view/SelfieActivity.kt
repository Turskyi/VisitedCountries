package ua.turskyi.visitedcountries.features.selfie.view

import android.os.Bundle
import androidx.core.content.ContextCompat
import ua.turskyi.visitedcountries.R
import ua.turskyi.visitedcountries.common.di.qualifiers.ViewModelInjection
import ua.turskyi.visitedcountries.common.ui.base.BaseActivity
import ua.turskyi.visitedcountries.databinding.ActivitySelfieBinding
import ua.turskyi.visitedcountries.features.selfie.viewmodel.SelfieActivityViewModel
import javax.inject.Inject

class SelfieActivity : BaseActivity() {
    override fun layoutRes() = R.layout.activity_selfie

    @Inject
    @field:ViewModelInjection
    lateinit var viewModel: SelfieActivityViewModel
    private lateinit var binding: ActivitySelfieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListeners()
    }
    private fun initView() {
        binding = ActivitySelfieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorBlack)
    }

    private fun initListeners() {
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}