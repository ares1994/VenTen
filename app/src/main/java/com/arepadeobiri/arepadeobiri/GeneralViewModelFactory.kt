package com.arepadeobiri.arepadeobiri

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GeneralViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(StandardViewModel::class.java) -> StandardViewModel(
                application
            ) as T

            else -> throw IllegalArgumentException("Unknown viewModel Class")
        }
    }
}