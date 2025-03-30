package org.example

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {
    private lateinit var fetchButton: Button

    private val dataReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val fact = intent?.getStringExtra("fact")
            // Navigate to ResultActivity with the fetched data
            val resultIntent = Intent(this@MainActivity, ResultActivity::class.java)
            resultIntent.putExtra("fact", fact)
            startActivity(resultIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchButton = findViewById(R.id.fetch_button)
        fetchButton.setOnClickListener {
            // Start WorkManager to fetch data
            val workRequest = OneTimeWorkRequestBuilder<FetchDataWorker>().build()
            WorkManager.getInstance(this).enqueue(workRequest)
        }
    }

    override fun onStart() {
        super.onStart()
        LocalBroadcastManager.getInstance(this).registerReceiver(dataReceiver, IntentFilter("DATA_FETCHED"))
    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(dataReceiver)
    }
}
