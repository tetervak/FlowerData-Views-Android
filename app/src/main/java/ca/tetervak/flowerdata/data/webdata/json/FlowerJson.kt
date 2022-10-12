package ca.tetervak.flowerdata.data.webdata.json

data class FlowerJson(
    val id: String,
    val label: String,
    val price: String,
    val text: String,
    val pictures: PicturesJson,
    val wiki: String
)