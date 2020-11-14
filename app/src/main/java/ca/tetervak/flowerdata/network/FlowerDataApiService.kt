package ca.tetervak.flowerdata.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL
        = "http://tetervak.dev.fast.sheridanc.on.ca/Examples/jQuery/Flowers3/data/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface FlowerDataApiService {
    @GET("catalog.json")
    fun getFlowers(): Call<String>
}

object FlowerDataApi {
    val retrofitService : FlowerDataApiService by lazy {
        retrofit.create(FlowerDataApiService::class.java) }
}