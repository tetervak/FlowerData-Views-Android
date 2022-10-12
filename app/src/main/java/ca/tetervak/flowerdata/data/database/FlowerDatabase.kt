package ca.tetervak.flowerdata.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FlowerEntity::class], version = 1, exportSchema = false)
abstract class FlowerDatabase : RoomDatabase() {

    abstract fun flowerDao(): FlowerDao
}
