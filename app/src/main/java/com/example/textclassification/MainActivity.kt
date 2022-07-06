package com.example.textclassification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.textclassification.databinding.ActivityMainBinding
import com.example.textclassification.helpers.TextClassificationClient

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        messageAdapter = MessageAdapter(mutableListOf()) {
            Toast.makeText(baseContext, it.text, Toast.LENGTH_SHORT).show()
        }
        binding.recyclerViewMessages.adapter = messageAdapter
        val client = TextClassificationClient(baseContext)
        client.load()
        val messages = mutableListOf<Message>()
        binding.buttonSendText.setOnClickListener {
            val text = binding.editTextInput.text.toString()
            val results = client.classify(text)
            val score = results[1].score
            if (score > 0.8) {
                binding.textViewOutput.text = "Su mensaje fue detectado como spam con una puntuación de $score y no fue enviado."
            } else {
                binding.textViewOutput.text = "¡Mensaje enviado! \nLa puntuación de spam fue: $score"
                messages.add(Message("User ${(0..100).random()}", text))
                messageAdapter.addMessages(messages)
            }
        }
    }
}