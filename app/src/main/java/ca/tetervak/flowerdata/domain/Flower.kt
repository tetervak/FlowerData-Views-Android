package ca.tetervak.flowerdata.domain

data class Flower(
    val label: String,
    val text: String,
    val picture: String,
    val id: Long = 0L
)