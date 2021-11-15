package ca.tetervak.flowerdata.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.repository.FlowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FlowerDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: FlowerRepository
): ViewModel() {

    private val flowerId: String = savedStateHandle["flowerId"] ?:
        throw IllegalArgumentException("missing flower id")

    val flower: LiveData<Flower> = liveData {
            emit(repository.get(flowerId))
        }
}