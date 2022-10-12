package ca.tetervak.flowerdata.data.webdata

import ca.tetervak.flowerdata.data.webdata.json.CatalogJson
import retrofit2.http.GET

interface WebDataApi {
    @GET("catalog.json")
    suspend fun getCatalog(): CatalogJson
}