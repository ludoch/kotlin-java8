package com.google.appengine.demo.androidclient

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.view.inputmethod.EditorInfo.IME_ACTION_UNSPECIFIED
import kotlinx.android.synthetic.main.activity_hello_world.nameInput
import kotlinx.android.synthetic.main.activity_hello_world.resultView
import java.net.URL

class HelloWorldActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hello_world)
    nameInput.setOnEditorActionListener { view, actionId, event ->
      if (actionId == IME_ACTION_DONE || actionId == IME_ACTION_UNSPECIFIED) {
        // We get UNSPECIFIED if we hit the Return key in the emulator.
        LookUpName().execute(view.text.toString())
        true
      } else {
        false
      }
    }
  }

  private inner class LookUpName : AsyncTask<String, Unit, String>() {
    override fun onPreExecute() {
      resultView.text = ""
    }

    override fun doInBackground(vararg name: String): String {
      val url = URL("http://kotlin-java8.appspot.com/lookup?name=${name[0]}")
      try {
        return url.readText()
      } catch (e: Exception) {
        return e.toString()
      }
    }

    /**
     * Called on the UI thread with the `String` result computed by [doInBackground].
     */
    override fun onPostExecute(result: String) {
      resultView.text = result
    }
  }
}
