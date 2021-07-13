package ca.tetervak.flowerdata.ui.list

import androidx.lifecycle.*
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.repository.FlowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchPriceViewModel @Inject constructor(
    val repository: FlowerRepository
) : ViewModel() {

    private val priceMargin = MutableLiveData<Float>(1F)

    val searchResultList: LiveData<List<Flower>> = priceMargin.switchMap { margin ->
        repository.getAll().map { list ->
            list.filter { flower ->
                flower.price.substring(1).toFloat() < margin
            }
        }
    }

    fun setPriceMargin(margin: Float){
        priceMargin.value = margin
    }

}