package ca.tetervak.flowerdata.data.repository

import ca.tetervak.flowerdata.data.database.FlowerDao
import ca.tetervak.flowerdata.data.database.FlowerEntity
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.data.webdata.json.CatalogJson
import ca.tetervak.flowerdata.data.webdata.json.FlowerJson
import ca.tetervak.flowerdata.data.webdata.UrlData
import ca.tetervak.flowerdata.data.webdata.WebDataApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FlowerRepositoryWebRoom @Inject constructor(
    private val flowerDao: FlowerDao,
    private val webDataApi: WebDataApi
) : FlowerRepository {

    override fun getAllFlowersFlow(): Flow<List<Flower>> =
        flowerDao.getAllFlowerEntitiesFlow()
            .map { entityList ->
                entityList.map { entity ->
                    entity.asFlower()
                }
            }.flowOn(Dispatchers.IO)

    override fun getFlowerByIdFlow(id: String): Flow<Flower> =
        flowerDao.getFlowerEntityByIdFlow(id)
            .map { entity -> entity.asFlower() }
            .flowOn(Dispatchers.IO)

    override suspend fun refresh() {
        val catalog: CatalogJson = webDataApi.getCatalog()
        val entityList: List<FlowerEntity> =
            catalog.flowers.map { flowerJson ->
                flowerJson.asEntity()
            }
        flowerDao.refresh(entityList)
    }

    override suspend fun clear() {
        flowerDao.deleteAllFlowerEntities()
    }
}

fun FlowerEntity.asFlower() =
    Flower(
        id = id,
        label = label,
        price = price,
        text = text,
        imageUrl = UrlData.IMAGE_FOLDER_URL + imageFile,
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