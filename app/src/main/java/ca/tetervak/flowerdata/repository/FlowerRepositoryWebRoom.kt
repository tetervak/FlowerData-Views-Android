package ca.tetervak.flowerdata.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ca.tetervak.flowerdata.database.FlowerDao
import ca.tetervak.flowerdata.database.FlowerEntity
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.network.CatalogJson
import ca.tetervak.flowerdata.network.FlowerDataApi
import ca.tetervak.flowerdata.network.FlowerJson
import ca.tetervak.flowerdata.network.IMAGE_FOLDER_URL
import javax.inject.Inject

class FlowerRepositoryWebRoom @Inject constructor(
    private val flowerDao: FlowerDao
) : FlowerRepository {

    override fun getAll(): LiveData<List<Flower>> =
        flowerDao.getAll().map { entityList ->
            entityList.map { entity ->
                entity.asFlower()
            }
        }

    override fun get(id: Int): LiveData<Flower> =
        flowerDao.get(id).map { entity ->
            entity.asFlower()
        }

    override suspend fun refresh() {
        val catalog: CatalogJson = FlowerDataApi.retrofitService.getCatalog()
        val entityList: List<FlowerEntity> =
            catalog.flowers.map { flowerJson ->
                flowerJson.asEntity()
            }
        flowerDao.deleteAll()
        flowerDao.insert(entityList)
    }
}

fun FlowerEntity.asFlower() =
    Flower(
        id = id,
        label = label,
        price = "$$price",
        text = text,
        imageUrl = IMAGE_FOLDER_URL + imageFile,
        wikiUrl = wikiUrl
    )

fun FlowerJson.asEntity() =
    FlowerEntity(
        id = 0,
        label = label,
        price = price.substring(1).toFloat(),
        text = text,
        imageFile = pictures.large,
        wikiUrl = wiki
    )