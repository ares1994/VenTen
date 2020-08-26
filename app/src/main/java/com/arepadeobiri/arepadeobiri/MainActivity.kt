package com.arepadeobiri.arepadeobiri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val filterRecyclerAdapter = FilterRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewModelFactory =
            GeneralViewModelFactory(application)


        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(StandardViewModel::class.java)


        recyclerView.adapter = filterRecyclerAdapter


        //Receive CSV File
        viewModel.csvFile.observe(this, Observer {


        })


        //Receive Filter information and display in recyclerView
        viewModel.filters.observe(this, Observer {


            filterRecyclerAdapter.submitList(it)
        })


        //Get Filters
        viewModel.getFilters()

        //Get CSV
        viewModel.downloadCsv(this.externalCacheDir)


    }
}