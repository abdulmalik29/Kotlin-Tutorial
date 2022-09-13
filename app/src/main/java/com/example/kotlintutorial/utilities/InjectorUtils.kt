package com.example.kotlintutorial.utilities

import com.example.kotlintutorial.Data.FakeDatabase
import com.example.kotlintutorial.UI.Quotes.QuotesViewModelFactory

object InjectorUtils {

    fun  provideQuotesViewModelFactory(): QuotesViewModelFactory {
        val quoteRepository = QuoteRepository.getInstance(FakeDatabase.getInstance().quoteDao)
        return QuotesViewModelFactory(quoteRepository)
    }

}