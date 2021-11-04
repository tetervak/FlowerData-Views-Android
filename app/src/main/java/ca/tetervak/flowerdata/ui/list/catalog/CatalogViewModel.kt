package ca.tetervak.flowerdata.ui.list.catalog

import androidx.lifecycle.*
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.repository.FlowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    repository: FlowerRepository
) : ViewModel() {
    val flowerList: LiveData<List<Flower>> = repository.getAll()
}