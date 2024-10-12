package com.example.quotesapplication

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class QuotesViewModel(private val context: Context): ViewModel() {
    private var quotesList: Array<Quote> = emptyArray()
    private var quoteIndex = 0
    
    
    init {
        quotesList = loadQuotesListFromAssets()
    }

    private fun loadQuotesListFromAssets(): Array<Quote> {
     val loadAssets = context.assets.open("quotes.json")
        val buffer = ByteArray(loadAssets.available())
        loadAssets.read(buffer)
        loadAssets.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote() = quotesList[quoteIndex]
    fun nextQuote() = quotesList[++quoteIndex]
    fun previousQuote() = quotesList[--quoteIndex]


}