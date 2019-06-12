package com.epam.task5

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.epam.task5.ShowTextActivity.Companion.KEY_EXTRA_MESSAGE

class MainActivity : AppCompatActivity() {

    private lateinit var mTextView: TextView
    private lateinit var mEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.changeTextBt).setOnClickListener(::setText)
        findViewById<View>(R.id.activityChangeTextBtn).setOnClickListener(::openAndSetText)

        mTextView = findViewById(R.id.textToBeChanged)
        mEditText = findViewById(R.id.editTextUserInput)
    }

    fun setText(view: View) {
        mTextView.text = mEditText.text.toString()
    }

    fun openAndSetText(view: View) {
        val newIntent = Intent(applicationContext, ShowTextActivity::class.java)
        newIntent.putExtra(KEY_EXTRA_MESSAGE, mEditText.text.toString())
        startActivity(newIntent)
    }
}
