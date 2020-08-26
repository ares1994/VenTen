package com.arepadeobiri.arepadeobiri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.arepadeobiri.arepadeobiri.databinding.ActivityFilteredBinding

class FilteredActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityFilteredBinding>(this, R.layout.activity_filtered)
    }
}