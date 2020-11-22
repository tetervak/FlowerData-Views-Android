package ca.tetervak.flowerdata.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.network.FlowerDataApi
import ca.tetervak.flowerdata.network.FlowerJson
import ca.tetervak.flowerdata.network.IMAGE_FOLDER_URL
import javax.inject.Inject

class FlowerRepositoryImpl @Inject constructor(): FlowerRepository {

    private var flowerListData: LiveData<List<Flower>> = liveData {
        val catalog = FlowerDataApi.retrofitService.getCatalog()
        val flowers = catalog.flowers.mapIndexed { index, flowerJson ->
            flowerJson.asFlower(index)
        }
        emit(flowers)
    }

    override fun getAll(): LiveData<List<Flower>> = flowerListData

    override fun get(id: Long): LiveData<Flower> =
        Transformations.map(flowerListData) { it[id.toInt()] }

}

fun FlowerJson.asFlower(index: Int): Flower{
    return Flower(
        label, price, text, IMAGE_FOLDER_URL + pictures.large, index.toLong())
}