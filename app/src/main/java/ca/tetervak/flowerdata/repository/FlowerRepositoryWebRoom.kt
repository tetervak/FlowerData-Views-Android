package ca.tetervak.flowerdata.repository

import ca.tetervak.flowerdata.database.FlowerDao
import ca.tetervak.flowerdata.database.FlowerEntity
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.network.CatalogJson
import ca.tetervak.flowerdata.network.FlowerDataApi
import ca.tetervak.flowerdata.network.FlowerJson
import ca.tetervak.flowerdata.network.IMAGE_FOLDER_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FlowerRepositoryWebRoom @Inject constructor(
    private val flowerDao: FlowerDao
) : FlowerRepository {

    override fun getAll(): Flow<List<Flower>> =
        flowerDao.getAll()
            .map { entityList ->
                entityList.map { entity ->
                    entity.asFlower()
                }
            }.flowOn(Dispatchers.IO)

    override fun get(id: String): Flow<Flower> =
        flowerDao.get(id)
            .map { entity -> entity.asFlower() }
            .flowOn(Dispatchers.IO)

    override suspend fun refresh() {
        val catalog: CatalogJson = FlowerDataApi.retrofitService.getCatalog()
        val entityList: List<FlowerEntity> =
            catalog.flowers.map { flowerJson ->
                flowerJson.asEntity()
            }
        flowerDao.refresh(entityList)
    }

    override suspend fun clear() {
        flowerDao.deleteAll()
    }
}

fun FlowerEntity.asFlower() =
    Flower(
        id = id,
        label = label,
        price = price,
        text = text,
        imageUrl = IMAGE_FOLDER_URL + imageFile,
        wikiUrl = wikiUrl
    )



fun FlowerJson.asEntity() =
    FlowerEntity(
        id = id,
        label = label,
        price = price.substring(1).toFloat(),
        text = text,
        imageFile = pictures.large,
        wikiUrl = wiki
    )