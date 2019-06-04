package com.epam.task3

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class Task3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task3)
        SomeTask(this).execute(resources.getString(R.string.greetinng))
    }

    class SomeTask(context: Activity) : AsyncTask<String, Void, String>() {
        private var textView: TextView = context.findViewById(R.id.greeting_text)
        override fun doInBackground(vararg params: String?): String? =
            params[0]

        override fun onPostExecute(result: String?) {
            textView.text = result
        }
    }
}
