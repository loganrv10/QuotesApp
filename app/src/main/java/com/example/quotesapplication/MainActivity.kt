package com.example.quotesapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: QuotesViewModel
    private val quotesText : TextView
        get() = findViewById(R.id.quoteText)
    private val quotesAuthor : TextView
        get() = findViewById(R.id.quoteAuthor)
    private val nextButton : Button
        get() = findViewById(R.id.btn_next)
    private val nextPrevious : Button
        get() = findViewById(R.id.btn_previous)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this, QuotesViewModelProvider(application))[QuotesViewModel::class.java]
        setQuote(mainViewModel.getQuote())

        nextButton.setOnClickListener {
            setQuote(mainViewModel.nextQuote())
        }
        nextPrevious.setOnClickListener {
            setQuote(mainViewModel.previousQuote())
        }

    }

    private fun setQuote(quote: Quote){
      quotesText.text = quote.text
        quotesAuthor.text = quote.author
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        startActivity(intent)
    }


}