package ca.tetervak.flowerdata.repository

import androidx.lifecycle.LiveData
import ca.tetervak.flowerdata.domain.Flower

interface FlowerRepository {
    fun getFlowers(): LiveData<List<Flower>>
}