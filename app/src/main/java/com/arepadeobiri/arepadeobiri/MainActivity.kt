package com.arepadeobiri.arepadeobiri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arepadeobiri.arepadeobiri.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), FilterRecyclerAdapter.FilterOnClickedListener {
    private val filterRecyclerAdapter = FilterRecyclerAdapter(this)
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: StandardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)


        val viewModelFactory =
            GeneralViewModelFactory(application)


        viewModel =
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

    override fun onClicked() {
        if (viewModel.csvFile.value == null) {
            Util.getSnackBar(binding.root, "Car Owners source data not found")
            return
        }

        startActivity(Intent(this, FilteredActivity::class.java))
    }
}