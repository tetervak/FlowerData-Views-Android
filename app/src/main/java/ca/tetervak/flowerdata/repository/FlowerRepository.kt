package ca.tetervak.flowerdata.repository

import androidx.lifecycle.LiveData
import ca.tetervak.flowerdata.domain.Flower

interface FlowerRepository {
    fun getAll(): LiveData<List<Flower>>
    fun get(id: String): LiveData<Flower>
    suspend fun refresh()
    suspend fun clear()
}