package ca.tetervak.flowerdata.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FlowerDao {

    @Query("SELECT * FROM flowers")
    fun getAll(): Flow<List<FlowerEntity>>

    @Query("SELECT * FROM flowers WHERE id = :id")
    fun get(id: String): Flow<FlowerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<FlowerEntity>)

    @Query("DELETE FROM flowers")
    suspend fun deleteAll()

    @Transaction
    suspend fun refresh(list: List<FlowerEntity>){
        deleteAll()
        insert(list)
    }
}