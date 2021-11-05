package ca.tetervak.flowerdata.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val TAG = "DataBaseModule"

    @Provides
    fun provideFlowerDao(database: FlowerDatabase): FlowerDao {
        Log.d(TAG, "provideFlowerDao: the FlowerDao object is returned")
        return database.flowerDao()
    }

    @Singleton
    @Provides
    fun provideFlowerDatabase(@ApplicationContext context: Context): FlowerDatabase {
        Log.d(TAG, "provideFlowerDatabase: the database object is created")
        return Room.databaseBuilder(
            context,
            FlowerDatabase::class.java,
            "flower_database"
        ).build()
    }
}