package ca.tetervak.flowerdata.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FlowerDao {

    @Query("SELECT * FROM flowers")
    fun getAllFlowerEntitiesFlow(): Flow<List<FlowerEntity>>

    @Query("SELECT * FROM flowers WHERE id = :id")
    fun getFlowerEntityByIdFlow(id: String): Flow<FlowerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlowerEntityList(list: List<FlowerEntity>)

    @Query("DELETE FROM flowers")
    suspend fun deleteAllFlowerEntities()

    @Transaction
    suspend fun refresh(list: List<FlowerEntity>){
        deleteAllFlowerEntities()
        insertFlowerEntityList(list)
    }
}