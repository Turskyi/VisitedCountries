package ua.turskyi.visitedcountries.features.selfie

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.intentFor
import ua.turskyi.visitedcountries.R
import ua.turskyi.visitedcountries.common.di.qualifiers.ViewModelInjection
import ua.turskyi.visitedcountries.common.ui.base.BaseActivity
import javax.inject.Inject

class SelfieActivity : BaseActivity() {
    override fun layoutRes() = R.layout.activity_selfie

    companion object {
        fun getIntent(activity: Activity) = activity.intentFor<SelfieActivity>()
    }

    @Inject
    @field:ViewModelInjection
    lateinit var viewModel: SelfieActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}