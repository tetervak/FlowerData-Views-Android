package ca.tetervak.flowerdata

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import ca.tetervak.flowerdata.workmanager.setupRefreshWork
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class FlowerDataApplication: Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        setupRefreshWork(this)
    }

}