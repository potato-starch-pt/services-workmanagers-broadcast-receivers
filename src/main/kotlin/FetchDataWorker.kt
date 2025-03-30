package org.example

import android.content.Context
import android.content.Intent
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.net.URL

class FetchDataWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        // Simulate network delay
        Thread.sleep(2000)

        // Fetch data from API
        val url = "https://catfact.ninja/fact"
        val response = URL(url).readText()

        // Send data back to UI
        val intent = Intent("DATA_FETCHED")
        intent.putExtra("fact", response)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)

        return Result.success()
    }
}
