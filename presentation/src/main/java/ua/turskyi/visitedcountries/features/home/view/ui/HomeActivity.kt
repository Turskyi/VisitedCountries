package ua.turskyi.visitedcountries.features.home.view.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.material.snackbar.Snackbar
import splitties.activities.start
import splitties.toast.longToast
import ua.turskyi.domain.model.Country
import ua.turskyi.visitedcountries.R
import ua.turskyi.visitedcountries.common.di.qualifiers.ViewModelInjection
import ua.turskyi.visitedcountries.common.ui.base.BaseActivity
import ua.turskyi.visitedcountries.databinding.ActivityHomeBinding
import ua.turskyi.visitedcountries.features.allcountries.view.ui.AllCountriesActivity
import ua.turskyi.visitedcountries.features.home.extentions.config
import ua.turskyi.visitedcountries.features.home.extentions.spToPix
import ua.turskyi.visitedcountries.features.home.view.adapter.HomeAdapter
import ua.turskyi.visitedcountries.features.home.view.callback.OnVisitedCountryClickListener
import ua.turskyi.visitedcountries.features.home.viewmodel.HomeActivityViewModel
import ua.turskyi.visitedcountries.features.selfie.view.SelfieActivity
import ua.turskyi.visitedcountries.utils.IntFormatter
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    @field:ViewModelInjection
    lateinit var viewModel: HomeActivityViewModel

    private lateinit var binding: ActivityHomeBinding

    private var adapter = HomeAdapter()
    private var mSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListeners()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotVisitedCountFromDB()
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = this.viewModel
        setSupportActionBar(binding.toolbar)
        val layoutManager = LinearLayoutManager(this)
        binding.includeLayout.rvVisitedCountries.adapter = this.adapter
        binding.includeLayout.rvVisitedCountries.layoutManager = layoutManager
    }

    private fun initListeners() {
        adapter.setOnItemClickListener(object :
            OnVisitedCountryClickListener {
            override fun onItemClick(country: Country) = start<SelfieActivity>()
            override fun onItemLongClick(country: Country) = showSnackBar(country)

            private fun showSnackBar(country: Country) {
                mSnackBar = Snackbar.make(
                    binding.includeLayout.rvVisitedCountries,
                    getString(R.string.delete_country, country.name),
                    Snackbar.LENGTH_LONG
                ).setActionTextColor(Color.WHITE).setAction(getString(R.string.yes)) {
                    removeOnLongClick(country)
                    longToast(getString(R.string.deleted, country.name))
                }
                mSnackBar?.config(applicationContext)
                mSnackBar?.show()

                val snackView = mSnackBar?.view
                val snackTextView =
                    snackView?.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                snackTextView?.setTextColor(Color.RED)
            }

            private fun removeOnLongClick(country: Country) {
                viewModel.removeFromVisited(country)
            }
        })
    }

    private fun initObservers() {
        viewModel.navigateToAllCountries.observe(this,
            { shouldNavigate ->
                if (shouldNavigate == true) {
                    start<AllCountriesActivity>()
                    viewModel.onNavigatedToAllCountries()
                }
            })
        viewModel.visitedCountries.observe(
            this,
            { visitedCountries ->
                initPieChart(visitedCountries)
                updateAdapter(visitedCountries)
                showFloatBtn(visitedCountries)
            })
        adapter.visibilityLoader.observe(this, { currentVisibility ->
            binding.pb.visibility = currentVisibility
        })
    }

    private fun showFloatBtn(visitedCountries: List<Country>?) {
        if (visitedCountries.isNullOrEmpty()) {
            binding.floatBtnLarge.show()
            binding.floatBtnSmall.visibility = View.GONE
        } else {
            binding.floatBtnLarge.hide()
            binding.floatBtnSmall.show()
        }
    }

    private fun initPieChart(visitedCountries: List<Country>) {
        val entries: MutableList<PieEntry> = mutableListOf()
        entries.add(PieEntry(visitedCountries.size.toFloat()))
        entries.add(PieEntry(viewModel.notVisitedCount.toFloat()))
        val pieChartColors: MutableList<Int> = mutableListOf()
        pieChartColors.add(ContextCompat.getColor(applicationContext, R.color.colorAccent))
        pieChartColors.add(
            ContextCompat.getColor(
                applicationContext,
                R.color.colorPrimaryDark
            )
        )
        val dataSet = PieDataSet(entries, null)
        dataSet.colors = pieChartColors

        val data = PieData(dataSet)
        data.setValueFormatter(IntFormatter())
        data.setValueTextSize(applicationContext.spToPix(R.dimen.caption))
        data.setValueTextColor(Color.WHITE)

        binding.apply {
            pieChart.data = data
            pieChart.description.isEnabled = false

            /* remove hole inside */
            pieChart.isDrawHoleEnabled = false

            /* removes color squares */
            pieChart.legend.isEnabled = false

            /* remove default text "no chart data available */
            pieChart.setNoDataText(null)

            /* rotate the pie chart to 45 degrees */
            pieChart.rotationAngle = -10f

            /* updates data in pieChart */
            pieChart.invalidate()
        }
    }


    private fun updateAdapter(countries: List<Country>) {
        adapter.setData(countries)
        binding.toolbarLayout.title = resources.getQuantityString(
            R.plurals.numberOfCountriesVisited,
            countries.size,
            countries.size
        )
    }
}
