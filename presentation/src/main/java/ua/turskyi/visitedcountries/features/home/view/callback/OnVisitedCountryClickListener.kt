package ua.turskyi.visitedcountries.features.home.view.callback

import ua.turskyi.domain.model.Country

interface OnVisitedCountryClickListener {
    fun onItemClick(country: Country)
    fun onItemLongClick(country: Country)
}