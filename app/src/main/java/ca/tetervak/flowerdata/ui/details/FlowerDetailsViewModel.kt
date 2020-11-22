package ca.tetervak.flowerdata.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.repository.FlowerRepository

class FlowerDetailsViewModel @ViewModelInject constructor(repository: FlowerRepository): ViewModel() {

    private var _flowerId = MutableLiveData<Long>()

    val flower: LiveData<Flower> =
        Transformations.switchMap(_flowerId){ repository.get(it) }

    fun loadData(envelopeId: Long){ _flowerId.value = envelopeId }
}