package ca.tetervak.flowerdata.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.flowerdata.network.Flower
import ca.tetervak.flowerdata.network.FlowerDataApi
import kotlinx.coroutines.launch
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response

class FlowerListViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _flowers = MutableLiveData<List<Flower>>()

    // The external immutable LiveData for the response String
    val flowers: LiveData<List<Flower>> = _flowers

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getFlowers()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getFlowers() {

        viewModelScope.launch {
            try {
                val catalog = FlowerDataApi.retrofitService.getCatalog()
                _flowers.value = catalog.flowers
            } catch (e: Exception) {
                //_flowers.value = "Failure: ${e.message}"
            }
        }

    }
    



}