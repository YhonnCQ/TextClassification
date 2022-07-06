package com.example.textclassification.helpers

import android.content.Context
import android.util.Log
import org.tensorflow.lite.support.label.Category
import org.tensorflow.lite.task.text.nlclassifier.NLClassifier
import java.io.IOException


class TextClassificationClient(private val context: Context) {
    companion object {
        const val MODEL_PATH = "model.tflite"
        const val TAG = "TextClassificationClient"
    }

    lateinit var classifier: NLClassifier

    fun load() {
        try {
            classifier = NLClassifier.createFromFile(context, MODEL_PATH)
        } catch (e: IOException) {
            Log.e(TAG, e.message.toString())
        }
    }

    fun unload() {
        classifier.close()
    }

    fun classify(text: String): List<Category> {
        return classifier.classify(text)
    }
}