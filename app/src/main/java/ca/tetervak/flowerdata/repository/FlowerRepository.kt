package ca.tetervak.flowerdata.repository

import ca.tetervak.flowerdata.domain.Flower
import kotlinx.coroutines.flow.Flow

interface FlowerRepository {
    fun getAll(): Flow<List<Flower>>
    suspend fun get(id: String): Flower
    suspend fun refresh()
    suspend fun clear()
}