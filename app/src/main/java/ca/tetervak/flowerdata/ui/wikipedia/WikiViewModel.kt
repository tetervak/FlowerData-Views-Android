package ca.tetervak.flowerdata.ui.wikipedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.repository.FlowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WikiViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: FlowerRepository
): ViewModel() {

    private val flowerId: String = savedStateHandle["flowerId"] ?:
    throw IllegalArgumentException("missing flower id")

    val flower: LiveData<Flower> = repository.get(flowerId)
}