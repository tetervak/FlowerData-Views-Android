package ca.tetervak.flowerdata.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.tetervak.flowerdata.network.FlowerDataApi
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response

class FlowerListViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String> = _response

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

        _response.value = "Set the Mars API Response here!"

        FlowerDataApi.retrofitService.getFlowers().enqueue(
            object: Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = response.body()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }
        })

    }
    



}