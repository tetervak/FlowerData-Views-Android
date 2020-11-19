package ca.tetervak.flowerdata.ui.list

import androidx.lifecycle.*
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.network.FlowerDataApi
import androidx.lifecycle.liveData
import ca.tetervak.flowerdata.network.FlowerJson
import ca.tetervak.flowerdata.network.IMAGE_FOLDER_URL

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
    return Flower(
            label, price, text, IMAGE_FOLDER_URL + pictures.large, index.toLong())
}