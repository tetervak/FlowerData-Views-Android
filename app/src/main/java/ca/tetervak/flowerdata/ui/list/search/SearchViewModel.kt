package ca.tetervak.flowerdata.ui.list.search

import androidx.lifecycle.*
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.domain.findCheaperThan
import ca.tetervak.flowerdata.data.repository.FlowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val repository: FlowerRepository
) : ViewModel() {

    private val livePriceMargin = MutableLiveData<Float>(1F)
    fun setPriceMargin(margin: Float){
        livePriceMargin.value = margin
    }

    private val liveFlowerList: LiveData<List<Flower>> = repository.getAllFlowersFlow().asLiveData()
    val liveSearchResultList: LiveData<List<Flower>> =
        livePriceMargin.switchMap { margin ->
            liveFlowerList.map { list -> list.findCheaperThan(margin) }
        }
}