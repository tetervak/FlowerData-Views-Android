package ca.tetervak.flowerdata.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flowers")
data class FlowerEntity(

    @PrimaryKey
    val id: String,

    val label: String,
    val price: Float,
    val text: String,

    @ColumnInfo(name = "image_file")
    val imageFile: String,

    @ColumnInfo(name = "wiki_url")
    val wikiUrl: String
)