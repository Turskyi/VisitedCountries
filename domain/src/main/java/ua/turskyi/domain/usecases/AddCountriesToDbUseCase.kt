package ua.turskyi.domain.usecases

import ua.turskyi.domain.model.Country
import ua.turskyi.domain.repositories.CountriesRepository
import javax.inject.Inject

class AddCountriesToDbUseCase @Inject constructor(
    private val countriesRepository: CountriesRepository
) {
    fun execute(countries: List<Country>) {
        countriesRepository.addCountriesToDb(countries)
    }
}