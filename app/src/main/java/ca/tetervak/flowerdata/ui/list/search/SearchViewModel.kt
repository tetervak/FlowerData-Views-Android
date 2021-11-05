package ca.tetervak.flowerdata.ui.list.search

import androidx.lifecycle.*
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.repository.FlowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val repository: FlowerRepository
) : ViewModel() {

    private val priceMargin = MutableLiveData<Float>(1F)
    fun setPriceMargin(margin: Float){
        priceMargin.value = margin
    }

    private val flowerList: LiveData<List<Flower>> = repository.getAll()
    val searchResultList: LiveData<List<Flower>> =
        priceMargin.switchMap { margin ->
            flowerList.map { list ->
                list.filter { flower ->
                    flower.price.substring(1).toFloat() < margin
                }
            }
        }
}