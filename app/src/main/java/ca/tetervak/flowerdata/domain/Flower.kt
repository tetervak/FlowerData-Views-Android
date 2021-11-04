package ca.tetervak.flowerdata.domain

data class Flower(
        val id: Int,
        val label: String,
        val price: String,
        val text: String,
        val imageUrl: String,
        val wikiUrl: String
)