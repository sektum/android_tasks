package com.epam.task5

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ShowTextActivity : AppCompatActivity() {
    companion object {
        const val KEY_EXTRA_MESSAGE = "com.epam.task5.espresso.sample.MESSAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_text)
        val message = intent.getStringExtra(KEY_EXTRA_MESSAGE)

        findViewById<TextView>(R.id.showTextView).text = message
    }
}
