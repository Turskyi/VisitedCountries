package ua.turskyi.visitedcountries.features.allcountries.view.ui

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_all_countries.*
import ua.turskyi.domain.model.Country
import ua.turskyi.visitedcountries.R
import ua.turskyi.visitedcountries.common.di.qualifiers.ViewModelInjection
import ua.turskyi.visitedcountries.common.ui.base.BaseActivity
import ua.turskyi.visitedcountries.features.allcountries.view.adapter.AllCountriesAdapter
import ua.turskyi.visitedcountries.features.allcountries.viewmodel.AllCountriesActivityViewModel
import javax.inject.Inject

class AllCountriesActivity : BaseActivity() {

    override fun layoutRes() = R.layout.activity_all_countries

    @Inject
    @field:ViewModelInjection
    lateinit var viewModel: AllCountriesActivityViewModel

    private lateinit var adapter: AllCountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListeners()
        initObservers()
    }

    private fun initView() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorBlack)
        adapter = AllCountriesAdapter()
        adapter.submitList(viewModel.pagedList)
        val layoutManager = LinearLayoutManager(this)
        rvAllCountries.adapter = adapter
        rvAllCountries.layoutManager = layoutManager
    }

    private fun initListeners() {
        toolbar.setNavigationOnClickListener { onBackPressed() }
        adapter.onCountryClickListener = ::addToVisited
    }

    private fun addToVisited(country: Country) {
        country.visited = true
        viewModel.markAsVisited(country)
        onBackPressed()
    }

    private fun initObservers() {
        viewModel.countriesLiveData.observe(this, Observer { offlineCountries ->
            updateTitle(offlineCountries)
        })
    }

    private fun updateTitle(countries: List<Country>) {
        toolbarTitle.text = getString(R.string.num_of_countries, countries.size.toString())
    }
}