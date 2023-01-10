package com.Diyorbek.speakerhomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var listenTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listenTextView = findViewById(R.id.textListerner)
        findViewById<ImageView>(R.id.btnListen).setOnClickListener { listenVoice() }
        findViewById<ImageView>(R.id.btnClear).setOnClickListener { clearText() }
        findViewById<MaterialButton>(R.id.btnSwitch).setOnClickListener { nextActivity() }


    }

    private fun nextActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun listenVoice() {

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello!")
        try {
            startActivityForResult(intent, 100)
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            if (resultCode == RESULT_OK || data == null) {
                val result: ArrayList<String> =
                    data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)!!
                listenTextView.text = result[0]
                result[0]
            }
        }
    }

    private fun clearText() {
        listenTextView.text = ""
    }
}