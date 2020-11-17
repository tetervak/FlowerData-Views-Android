package ca.tetervak.flowerdata.network

data class FlowerJson(
    val name: String,
    val label: String,
    val text: String,
    val pictures: PicturesJson)