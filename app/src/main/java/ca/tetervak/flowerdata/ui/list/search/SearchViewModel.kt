package ca.tetervak.flowerdata.ui.list.search

import androidx.lifecycle.*
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.domain.findCheaperThan
import ca.tetervak.flowerdata.repository.FlowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val repository: FlowerRepository
) : ViewModel() {

    private val priceMargin = MutableLiveData<Float>(1F)
    fun setPriceMargin(margin: Float){
        priceMargin.value = margin
    }

    private val flowerList: LiveData<List<Flower>> = repository.getAll().asLiveData()
    val searchResultList: LiveData<List<Flower>> =
        priceMargin.switchMap { margin ->
            flowerList.map { list -> list.findCheaperThan(margin) }
        }
}