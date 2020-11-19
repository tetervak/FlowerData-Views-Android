package ca.tetervak.flowerdata.domain

data class Flower(
        val label: String,
        val price: String,
        val text: String,
        val imageUrl: String,
        val id: Long = 0L
)