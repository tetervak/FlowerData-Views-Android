package ca.tetervak.flowerdata.ui.details

import androidx.lifecycle.*
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.repository.FlowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FlowerDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: FlowerRepository
): ViewModel() {

    private val flowerId: Int = savedStateHandle["flowerId"] ?:
        throw IllegalArgumentException("missing flower id")

    val flower: LiveData<Flower> = repository.get(flowerId)
}