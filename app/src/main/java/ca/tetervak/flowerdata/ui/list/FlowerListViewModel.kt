package ca.tetervak.flowerdata.ui.list

import android.util.Log
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