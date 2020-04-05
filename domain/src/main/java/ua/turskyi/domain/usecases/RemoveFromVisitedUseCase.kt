package ua.turskyi.domain.usecases

import ua.turskyi.domain.model.Country
import ua.turskyi.domain.repository.CountriesRepository
import javax.inject.Inject

class RemoveFromVisitedUseCase @Inject constructor(
    private val countriesRepository: CountriesRepository
) {
    fun execute(country: Country) {
        countriesRepository.removeFromVisited(country)
    }
}