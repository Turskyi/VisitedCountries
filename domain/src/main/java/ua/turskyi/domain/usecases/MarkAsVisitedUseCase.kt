package ua.turskyi.domain.usecases

import ua.turskyi.domain.model.Country
import ua.turskyi.domain.repositories.CountriesRepository
import javax.inject.Inject

class MarkAsVisitedUseCase @Inject constructor(
    private val countriesRepository: CountriesRepository
) {
    fun execute(country: Country) {
        countriesRepository.markAsVisited(country)
    }
}