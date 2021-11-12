package ca.tetervak.flowerdata.ui.list.catalog

import androidx.lifecycle.*
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.repository.FlowerRepository
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

    val flowerList: LiveData<List<Flower>> = repository.getAll()

    enum class Status { STARTED, REFRESHING, LOADED, ERROR }
    private val _status = MutableLiveData(Status.STARTED)
    val status: LiveData<Status> = _status

    fun refresh(){
        viewModelScope.launch(Dispatchers.IO){
            _status.postValue(Status.REFRESHING)
            delay(1500) // fake delay
            try{
                repository.refresh()
                _status.postValue(Status.LOADED)
            }catch(error: IOException){
                _status.postValue(Status.ERROR)
            }
        }
    }

    fun clear(){
        viewModelScope.launch(Dispatchers.IO){
            repository.clear()
            reset()
        }
    }

    fun reset(){
        _status.postValue(Status.STARTED)
    }
}