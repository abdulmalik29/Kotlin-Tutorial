package com.example.kotlintutorial.UI.Quotes

import QuoteRepository
import androidx.lifecycle.ViewModel
import com.example.kotlintutorial.Data.Quote


class QuotesViewModel (private val quoteRepository: QuoteRepository)
    : ViewModel(){

    fun getQuotes() = quoteRepository.getQuotes()
    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)

}