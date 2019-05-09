package com.akarbowy.cakelisttest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akarbowy.cakelisttest.R
import com.akarbowy.cakelisttest.injection.getViewModel
import com.akarbowy.cakelisttest.injection.injector

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        getViewModel { injector.mainViewModel }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.loadCakes()
    }
}