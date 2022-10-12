package ca.tetervak.flowerdata.data.repository

import ca.tetervak.flowerdata.domain.Flower
import kotlinx.coroutines.flow.Flow

interface FlowerRepository {
    fun getAllFlowersFlow(): Flow<List<Flower>>
    fun getFlowerByIdFlow(id: String): Flow<Flower>
    suspend fun refresh()
    suspend fun clearLocalData()
}