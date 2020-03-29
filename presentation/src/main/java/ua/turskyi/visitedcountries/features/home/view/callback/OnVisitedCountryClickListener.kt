package ua.turskyi.visitedcountries.features.home.view.callback

import ua.turskyi.domain.models.Country

interface OnVisitedCountryClickListener {
    fun onItemClick(country: Country)
    fun onItemLongClick(country: Country)
}