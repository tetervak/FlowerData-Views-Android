package ca.tetervak.flowerdata.ui.list.catalog

import androidx.lifecycle.*
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.data.repository.FlowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: FlowerRepository
) : ViewModel() {

    val liveFlowerList: LiveData<List<Flower>> = repository.getAllFlowersFlow().asLiveData()

    enum class Status { SUCCESS, LOADING, ERROR }

    private val _liveStatus = MutableLiveData(Status.SUCCESS)
    val liveStatus: LiveData<Status> = _liveStatus

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            _liveStatus.postValue(Status.LOADING)
            delay(1500) // fake delay
            try {
                repository.refresh()
                _liveStatus.postValue(Status.SUCCESS)
            } catch (error: IOException) {
                _liveStatus.postValue(Status.ERROR)
            }
        }
    }

    fun clearLocalData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearLocalData()
            _liveStatus.postValue(Status.SUCCESS)
        }
    }

    fun clearError() {
        _liveStatus.value = Status.SUCCESS
    }
}