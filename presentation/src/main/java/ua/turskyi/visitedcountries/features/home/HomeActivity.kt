package ua.turskyi.visitedcountries.features.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import ua.turskyi.visitedcountries.R
import ua.turskyi.visitedcountries.common.di.qualifiers.ViewModelInjection
import ua.turskyi.visitedcountries.common.ui.base.BaseActivity
import ua.turskyi.visitedcountries.databinding.ActivityHomeBinding
import ua.turskyi.visitedcountries.features.allcountries.AllCountriesActivity
import javax.inject.Inject

class HomeActivity : BaseActivity() {
    override fun layoutRes() = R.layout.activity_home
    private lateinit var binding: ActivityHomeBinding
    private var mSnackBar: Snackbar? = null
    @Inject
    @field:ViewModelInjection
    lateinit var viewModel: HomeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        initView()
        initListeners()
        initObservers()
    }
    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = this.viewModel
        setSupportActionBar(toolbar)
        val layoutManager = LinearLayoutManager(this)
        rvVisitedCountries.layoutManager = layoutManager
        rvVisitedCountries.setHasFixedSize(true)
    }

    private fun initListeners() {
//        adapter.setOnItemClickListener(object :
//            OnVisitedCountryClickListener {
//            override fun onItemClick(country: Country) {
//                startActivity(Intent(this@HomeActivity, SelfieActivity::class.java))
//            }
//
//            override fun onItemLongClick(country: Country) {
//                showSnackBar(country)
//            }
//
//            private fun showSnackBar(country: Country) {
//                mSnackBar = Snackbar.make(
//                    rvVisitedCountries,
//                    getString(R.string.delete_country, country.name),
//                    Snackbar.LENGTH_LONG
//                ).setActionTextColor(Color.WHITE).setAction(getString(R.string.yes)) {
//                    removeOnLongClick(country)
//
//                    Toast.makeText(
//                        applicationContext, getString(R.string.deleted, country.name),
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//                mSnackBar?.config(applicationContext)
//                mSnackBar?.show()
//
//                val snackView = mSnackBar!!.view
//                val snackTextView =
//                    snackView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
//                snackTextView.setTextColor(Color.RED)
//            }
//        })
    }

    private fun initObservers() {
        viewModel.navigateToAllCountries.observe(this,
            Observer { shouldNavigate ->
                if (shouldNavigate == true) {
                    startActivity(AllCountriesActivity.getIntent(this))
                    viewModel.onNavigatedToAllCountries()
                }
            })
//        viewModel.visitedCountriesFromRxDB.observe(
//            this,
//            Observer { visitedCountries ->
//                initPieChart(visitedCountries)
//                updateAdapter(visitedCountries)
//                showFloatBtn(visitedCountries)
//            })
//        adapter.visibilityLoader.observe(this, Observer { currentVisibility ->
//            pb.visibility = currentVisibility
//        })
    }
}
