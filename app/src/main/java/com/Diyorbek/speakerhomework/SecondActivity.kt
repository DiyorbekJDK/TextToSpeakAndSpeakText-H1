package com.Diyorbek.speakerhomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import java.util.*

class SecondActivity : AppCompatActivity() {
    private var textToSpeech: TextToSpeech? = null
    private lateinit var writeText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        speakText()
        findViewById<ImageView>(R.id.btnClear).setOnClickListener { clearText() }
        findViewById<MaterialButton>(R.id.btnSwitchHome).setOnClickListener { nextActivity() }

    }

    private fun speakText() {
        writeText = findViewById(R.id.textSpeaker)
        val btnSpeak: ImageView = findViewById(R.id.btnSpeak)
        textToSpeech = TextToSpeech(this) { status ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech?.language = Locale.US
            }
        }
        btnSpeak.setOnClickListener {
            val text = writeText.text.toString().trim()
            textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }

    }

    private fun clearText() {
        writeText.setText("")
    }

    private fun nextActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}