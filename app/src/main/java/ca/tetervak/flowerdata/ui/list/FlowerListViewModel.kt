package ca.tetervak.flowerdata.ui.list

import androidx.lifecycle.*
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.network.FlowerDataApi
import androidx.lifecycle.liveData
import ca.tetervak.flowerdata.network.FlowerJson

class FlowerListViewModel : ViewModel() {

    private var flowerListData: LiveData<List<Flower>>? = null

    fun getFlowers(): LiveData<List<Flower>> {
        return flowerListData ?: liveData {
            val catalog = FlowerDataApi.retrofitService.getCatalog()
            val flowers = catalog.flowers.mapIndexed { index, flowerJson ->
                flowerJson.asFlower(index)
            }
            emit(flowers)
        }.also {
            flowerListData = it
        }
    }
}

fun FlowerJson.asFlower(index: Int): Flower{
    return Flower(label, text, pictures.large, index.toLong())
}