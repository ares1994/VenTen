package com.arepadeobiri.arepadeobiri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arepadeobiri.arepadeobiri.dataModels.FilterItem
import com.arepadeobiri.arepadeobiri.databinding.ActivityFilteredBinding

class FilteredActivity : AppCompatActivity() {
    private lateinit var viewModel: StandardViewModel
    private val filteredRecyclerAdapter = FilteredRecyclerAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityFilteredBinding>(
                this,
                R.layout.activity_filtered
            )


        val viewModelFactory =
            GeneralViewModelFactory(application)


        viewModel =
            ViewModelProvider(this, viewModelFactory).get(StandardViewModel::class.java)


        binding.recyclerView.adapter = filteredRecyclerAdapter

        val filterItem = intent.getParcelableExtra<FilterItem>("filter")

        viewModel.downloadCsv(externalCacheDir)


        //Get CSV file and compare against selected filter to find valid carOwners
        viewModel.csvFile.observe(this, Observer {


            val list = Util.readCsvFile(it)

            val filteredList = filterItem?.let { Util.getFilteredCarOwners(it, list) }

            if (filteredList!!.isEmpty()) {
                Util.getSnackBar(binding.root, "There are no owners that match these filters")
                finish()
            } else {
                filteredRecyclerAdapter.submitList(filteredList)
            }

            binding.progressBar.visibility = View.INVISIBLE


        })


    }
}