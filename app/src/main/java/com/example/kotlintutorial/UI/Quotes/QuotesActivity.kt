package com.example.kotlintutorial.UI.Quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlintutorial.Data.Quote
import com.example.kotlintutorial.R
import com.example.kotlintutorial.utilities.InjectorUtils

class QuotesActivity : AppCompatActivity() {

    private lateinit var textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)
        initializeUi()
    }

    private fun initializeUi() {
        // Get the QuotesViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.provideQuotesViewModelFactory()
        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        val viewModel = ViewModelProviders.of(this, factory)
            .get(QuotesViewModel::class.java)

        // Observing LiveData from the QuotesViewModel which in turn observes
        // LiveData from the repository, which observes LiveData from the DAO ☺
        viewModel.getQuotes().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
            textView = findViewById(R.id.textView_quotes)
            textView.text = stringBuilder.toString()
        })

        // When button is clicked, instantiate a Quote and add it to DB through the ViewModel
        findViewById<Button>(R.id.button_add_quote).setOnClickListener {
            val quote = Quote(findViewById<TextView>(R.id.editText_quote).text.toString(), findViewById<TextView>(R.id.editText_author).text.toString())
            viewModel.addQuote(quote)
            findViewById<TextView>(R.id.editText_quote).text = ""
            findViewById<TextView>(R.id.editText_author).text = ""
        }
    }

}