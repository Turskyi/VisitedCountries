package ua.turskyi.visitedcountries.common.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel: ViewModel() {

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     * Since viewModelJob is passed, all coroutines launched by uiScope can be canceled by calling
     * viewModelJob.cancel()
     */
    val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        compositeDisposable.clear()
    }
}