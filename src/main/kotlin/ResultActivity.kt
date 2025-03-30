package org.example

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    private lateinit var factTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        factTextView = findViewById(R.id.fact_text_view)
        val fact = intent.getStringExtra("fact")
        factTextView.text = fact
    }
}
