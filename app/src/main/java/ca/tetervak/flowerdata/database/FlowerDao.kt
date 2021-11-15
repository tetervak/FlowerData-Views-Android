package ca.tetervak.flowerdata.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FlowerDao {

    @Query("SELECT * FROM flowers")
    fun getAll(): LiveData<List<FlowerEntity>>

    @Query("SELECT * FROM flowers WHERE id = :id")
    suspend fun get(id: String): FlowerEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<FlowerEntity>)

    @Query("DELETE FROM flowers")
    suspend fun deleteAll()
}