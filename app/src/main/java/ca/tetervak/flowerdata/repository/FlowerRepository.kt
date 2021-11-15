package ca.tetervak.flowerdata.repository

import androidx.lifecycle.LiveData
import ca.tetervak.flowerdata.domain.Flower

interface FlowerRepository {
    fun getAll(): LiveData<List<Flower>>
    suspend fun get(id: String): Flower
    suspend fun refresh()
    suspend fun clear()
}