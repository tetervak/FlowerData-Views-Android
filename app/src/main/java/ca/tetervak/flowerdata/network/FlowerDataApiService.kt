package ca.tetervak.flowerdata.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL
        = "http://tetervak.dev.fast.sheridanc.on.ca/Examples/jQuery/Flowers3/data/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FlowerDataApiService {
    @GET("catalog.json")
    suspend fun getCatalog(): CatalogJson
}

object FlowerDataApi {
    val retrofitService : FlowerDataApiService by lazy {
        retrofit.create(FlowerDataApiService::class.java) }
}