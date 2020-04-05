package ua.turskyi.domain.usecases

import ua.turskyi.domain.model.Country
import ua.turskyi.domain.repository.CountriesRepository
import javax.inject.Inject

class AddCountriesToDbUseCase @Inject constructor(
    private val countriesRepository: CountriesRepository
) {
    fun execute(countries: List<Country>) {
        val addingCountriesToDbInBackground =  countriesRepository.addCountriesToDb(countries)
        val thread = Thread(addingCountriesToDbInBackground)
        thread.start()
    }
}