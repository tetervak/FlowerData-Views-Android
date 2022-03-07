package ca.tetervak.flowerdata.domain

fun List<Flower>.findCheaperThan(margin: Float) =
    filter { flower -> flower.price < margin }