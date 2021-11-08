package ca.tetervak.flowerdata.domain

data class Flower(
        val id: String,
        val label: String,
        val price: Float,
        val text: String,
        val imageUrl: String,
        val wikiUrl: String
)