package ca.tetervak.flowerdata.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.network.CatalogJson
import ca.tetervak.flowerdata.network.FlowerDataApi
import ca.tetervak.flowerdata.network.FlowerJson
import ca.tetervak.flowerdata.network.IMAGE_FOLDER_URL
import javax.inject.Inject

class FlowerRepositoryWeb @Inject constructor() : FlowerRepository {

    private var flowerListData: LiveData<List<Flower>> =
        liveData {
            val catalog: CatalogJson = FlowerDataApi.retrofitService.getCatalog()
            val flowers: List<Flower> =
                catalog.flowers.mapIndexed { index, flowerJson ->
                    flowerJson.asFlower(index)
                }
            emit(flowers)
        }

    override fun getAll(): LiveData<List<Flower>> = flowerListData

    override fun get(id: Int): LiveData<Flower> =
        flowerListData.map { list -> list[id] }

    override suspend fun refresh() {

    }

}

fun FlowerJson.asFlower(index: Int) =
    Flower(
        index,
        label,
        price,
        text,
        IMAGE_FOLDER_URL + pictures.large,
        wiki
    )