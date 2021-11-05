package ca.tetervak.flowerdata.ui.list.catalog

import androidx.lifecycle.*
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.repository.FlowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: FlowerRepository
) : ViewModel() {

    val flowerList: LiveData<List<Flower>> = repository.getAll()

    fun refresh(){
        viewModelScope.launch(Dispatchers.IO){
            repository.refresh()
        }
    }

    fun clear(){
        viewModelScope.launch(Dispatchers.IO){
            repository.clear()
        }
    }
}