package ca.tetervak.flowerdata.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.network.FlowerDataApi
import ca.tetervak.flowerdata.network.FlowerJson
import ca.tetervak.flowerdata.network.IMAGE_FOLDER_URL
import javax.inject.Inject

class FlowerRepositoryImpl @Inject constructor(): FlowerRepository {

    private var flowerListData: LiveData<List<Flower>>? = null

    override fun getFlowers(): LiveData<List<Flower>> {
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