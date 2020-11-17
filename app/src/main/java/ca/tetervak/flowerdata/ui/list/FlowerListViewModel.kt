package ca.tetervak.flowerdata.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.flowerdata.network.FlowerJson
import ca.tetervak.flowerdata.network.FlowerDataApi
import kotlinx.coroutines.launch

class FlowerListViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _flowers = MutableLiveData<List<FlowerJson>>()

    // The external immutable LiveData for the response String
    val flowers: LiveData<List<FlowerJson>> = _flowers

    init {
        getFlowers()
    }

    private fun getFlowers() {

        viewModelScope.launch {
            try {
                val catalog = FlowerDataApi.retrofitService.getCatalog()
                _flowers.value = catalog.flowers
            } catch (e: Exception) {
                Log.e("NETWORK","Failure: ${e.message}")
            }
        }
    }
}