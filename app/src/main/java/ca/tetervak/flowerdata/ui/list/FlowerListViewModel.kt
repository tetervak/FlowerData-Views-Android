package ca.tetervak.flowerdata.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.network.FlowerDataApi
import androidx.lifecycle.liveData
import ca.tetervak.flowerdata.network.FlowerJson
import ca.tetervak.flowerdata.network.IMAGE_FOLDER_URL
import ca.tetervak.flowerdata.repository.FlowerRepository

class FlowerListViewModel @ViewModelInject constructor(
        private val repository: FlowerRepository
) : ViewModel() {

    val flowerList: LiveData<List<Flower>> = repository.getFlowers()

}